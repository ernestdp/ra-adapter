package com.ernest.reefangel.service;

import com.ernest.reefangel.slack.feedback.FeedBackDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ernest on 2017/01/29.
 */
@Service
public class FeedbackService {

    private FeedBackDelegate feedBackDelegate;

    @Autowired
    public FeedbackService(CommandService cloudCommandService)
    {
    }

    public String delegateFeedback(String text) throws IOException, InterruptedException {
        return feedBackDelegate.answer(text);
    }


}
