package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.Command;
import com.ernest.reefangel.domain.Port;
import com.ernest.reefangel.domain.PortAlias;
import com.ernest.reefangel.domain.PortMappings;
import com.ernest.reefangel.service.CommandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

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
        return request.trim().toLowerCase().contains("#stop");
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
                    return String.format("Unable to take a capture image. %s. Try again buy typing 'snapshot'.", e.getMessage());
                }
                return String.format("Ok, stopped %s", portAlias.name());
            }
        }
        return "Could not find the port to stop ";
    }
}
