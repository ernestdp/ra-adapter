package com.ernest.reefangel.service.feedback;

import org.springframework.stereotype.Component;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Component
public class FeedBackDelegate {

    private FeedBackResponse registry;

    public FeedBackDelegate() {
        buildRegistry();
    }


    public String answer(String input) {
        return registry.respond(input);
    }


    private void buildRegistry() {
        final StatusResponse statusResponse = new StatusResponse(null);
        registry = new HiResponse(statusResponse);
    }

}
