package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.FeedbackOptions;
import com.ernest.reefangel.domain.Port;
import com.ernest.reefangel.domain.PortAlias;
import com.ernest.reefangel.domain.PortMappings;
import com.ernest.reefangel.service.CommandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static com.ernest.reefangel.domain.FeedbackOptions.*;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class StartResponse extends FeedBackResponse {

    public StartResponse(CommandService commandService) {
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("#"+ start.name());
    }

    @Override
    String defineResponseMessage(String request) {
        for (PortAlias portAlias : PortAlias.values()) {
            if(request.trim().contains(portAlias.name()))
            {
                try {
                    commandService.start(portAlias.name());
                } catch (Exception e) {
                    log.error(e);
                    return String.format("%s %s ", error(), e.getMessage());
                }
                return String.format("Ok, started %s", portAlias.name());
            }
        }

    return error();
    }

    private String error(){
        final StringBuffer buffer = new StringBuffer("Could not find the port to start. Remember to type start and one of the following ports. \n ");
        for (PortAlias portAlias : PortAlias.values()) {
            buffer.append("`"+ portAlias.name() + "` ");
        }
        return buffer.toString();
    }
}
