package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.service.CommandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class StatusResponse extends FeedBackResponse {


    public StatusResponse(CommandService commandService) {
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("status");
    }

    @Override
    String defineResponseMessage(String request) {
        try {
            RA ra = commandService.statusAll();
        } catch (IOException e) {

            return "aaa I'm experiencing a communication error. Would you like to try again?";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
