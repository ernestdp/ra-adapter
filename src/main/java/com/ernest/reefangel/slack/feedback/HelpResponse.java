package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.FeedbackOptions;
import com.ernest.reefangel.service.CommandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import static com.ernest.reefangel.domain.FeedbackOptions.*;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class HelpResponse extends FeedBackResponse {


    public HelpResponse(CommandService commandService) {
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains(hi.name())
                || request.trim().toLowerCase().contains("#"+help.name());
    }

    @Override
    String defineResponseMessage(String request) {
        StringBuffer buffer =new StringBuffer();
        buffer.append("Type # and then one of these options :")
        .append("\n");
        for (FeedbackOptions feedbackOptions : values()) {
            buffer.append("`"+feedbackOptions.name()+"` ");
        }
        buffer.append("\n  For example type _#example_ ");
        return buffer.toString();
    }
}
