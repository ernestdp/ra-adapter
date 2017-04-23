package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.service.CommandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import static com.ernest.reefangel.domain.FeedbackOptions.hi;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class ExampleResponse extends FeedBackResponse {


    public ExampleResponse(CommandService commandService) {
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("#example");
    }

    @Override
    String defineResponseMessage(String request) {
        return "Yay!! You got it. Now type _#help_ to see all the options again. .";
    }
}
