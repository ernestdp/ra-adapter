package com.ernest.reefangel.service.feedback;

/**
 * Created by ernest8 on 06/02/2017.
 */
public class HiResponse extends FeedBackResponse {

    public HiResponse(FeedBackResponse feedBackResponseParent) {
        super(feedBackResponseParent);
    }

    @Override
    boolean isCondition(String request) {
        return false;
    }

    @Override
    String defineResponseMessage(String request) {
        return null;
    }
}
