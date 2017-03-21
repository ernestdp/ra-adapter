package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.service.CommandService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ernest8 on 06/02/2017.
 */
public abstract class FeedBackResponse {

    protected Logger log;
    CommandService commandService;

    @Autowired
    public FeedBackResponse(CommandService commandService) {
        this.commandService=commandService;
        log= Logger.getLogger(this.getClass());
    }

    public String respond(String request) {
        if (isCondition(request)) {
            return defineResponseMessage(request);
        }
        return "";
    }

    abstract boolean isCondition(String request);

    abstract String defineResponseMessage(String request);

}
