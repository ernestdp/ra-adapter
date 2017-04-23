package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.Port;
import com.ernest.reefangel.domain.PortMappings;
import com.ernest.reefangel.service.CommandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class RebootResponse extends FeedBackResponse {



    public RebootResponse(CommandService commandService) {
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("#reboot");
    }

    @Override
    String defineResponseMessage(String request) {
        try {
            commandService.command("boot");
        } catch (IOException e) {
            return e.getMessage();
        } catch (InterruptedException e) {
            return e.getMessage();
        }
        return "Rebooting controller, standby....";
    }
}
