package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.slack.SlackPushService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Component
public class FeedBackDelegate {

    private List<FeedBackResponse> responseList;
    private Logger log;

    @Autowired
    public FeedBackDelegate(List<FeedBackResponse> responses) {
        this.responseList=responses;
        this.log = Logger.getLogger(SlackPushService.class);

    }


    public String answer(String input) {
        log.info("Feedback input received : " +input);
        StringBuilder answer = new StringBuilder();
        for (FeedBackResponse feedBackResponse : responseList) {
            String respond = feedBackResponse.respond(input);
            answer.append(respond);
            answer.append("\n");
            log.info(input + ", "+feedBackResponse.getClass().getSimpleName()+" feedback added : " + respond);

        }
        return answer.toString();
    }

}
