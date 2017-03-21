package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.service.CommandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class HiResponse extends FeedBackResponse {

    public HiResponse(CommandService commandService) {
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("hi");
    }

    @Override
    String defineResponseMessage(String request) {
        return "Oh Hi. Anything I can help you with?";
    }
}
