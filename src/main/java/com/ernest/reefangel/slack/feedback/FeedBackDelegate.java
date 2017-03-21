package com.ernest.reefangel.slack.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Component
public class FeedBackDelegate {

    private List<FeedBackResponse> responseList;

    @Autowired
    public FeedBackDelegate(List<FeedBackResponse> responses) {
        this.responseList=responses;
    }


    public String answer(String input) {
        StringBuilder answer = new StringBuilder();
        for (FeedBackResponse feedBackResponse : responseList) {
            answer.append(feedBackResponse.respond(input));
            answer.append("\n");
        }
        return answer.toString();
    }

}
