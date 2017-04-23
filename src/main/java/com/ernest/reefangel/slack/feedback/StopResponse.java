package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.*;
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
public class StopResponse extends FeedBackResponse {

    public StopResponse(CommandService commandService) {
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("#"+ stop.name());
    }

    @Override
    String defineResponseMessage(String request) {
        for (PortAlias portAlias : PortAlias.values()) {
            if(request.trim().contains(portAlias.name()))
            {
                try {
                    commandService.stop(portAlias.name());
                } catch (Exception e) {
                    log.error(e);
                    return String.format("%s %s ", error(), e.getMessage());
                }
                return String.format("Ok, stopped %s", portAlias.name());
            }
        }
        return error();
    }

    private String error(){
        final StringBuffer buffer = new StringBuffer("Could not find the port to stop. Remember to type stop and one of the following ports. \n ");
        for (PortAlias portAlias : PortAlias.values()) {
            buffer.append("`"+ portAlias.name() + "` ");
        }
        return buffer.toString();
    }
}
