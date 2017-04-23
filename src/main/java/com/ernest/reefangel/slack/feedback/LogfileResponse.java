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
public class LogfileResponse extends FeedBackResponse {

    public LogfileResponse(CommandService commandService) {
        super(commandService);
    }

    @Autowired
    SlackFileUploadService fileUploadService;

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("#log");
    }

    @Override
    String defineResponseMessage(String request) {
            fileUploadService.sendFile("/var/log/ra.log");
            return "file attached....";
    }
}
