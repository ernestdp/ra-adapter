package com.ernest.reefangel.service.feedback;

import com.ernest.reefangel.service.CloudCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Component
public abstract class FeedBackResponse {

    private FeedBackResponse feedBackResponseParent;

    @Autowired
    public FeedBackResponse(FeedBackResponse feedBackResponseParent) {
        this.feedBackResponseParent = feedBackResponseParent;
    }

    public String respond(String request) {
        if (isCondition(request)) {
            return defineResponseMessage(request);
        }
        return null;
    }

    abstract boolean isCondition(String request);

    abstract String defineResponseMessage(String request);

}
