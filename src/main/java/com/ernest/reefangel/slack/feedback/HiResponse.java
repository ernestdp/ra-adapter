package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.service.CommandService;
import com.ernest.reefangel.slack.SlackFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class HiResponse extends FeedBackResponse {

    @Autowired
    private SlackFileUploadService slackFileUploadService;

    public HiResponse(CommandService commandService) {
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("hi");
    }

    @Override
    String defineResponseMessage(String request) {
        slackFileUploadService.sendFile();
        return "Oh Hi. How can I help ?";
    }
}
