package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.Command;
import com.ernest.reefangel.service.CommandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        return request.trim().toLowerCase().contains("stop");
    }

    @Override
    String defineResponseMessage(String request) {
        if(request.trim().toLowerCase().contains("skimmer"))
        {
            try {
                return commandService.command("/r10 ");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "ok stopped";
    }
}
