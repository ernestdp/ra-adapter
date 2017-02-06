package com.ernest.reefangel.service;

import com.ernest.reefangel.domain.Fields;
import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.service.feedback.FeedBackDelegate;
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
    public FeedbackService(CloudCommandService cloudCommandService)
    {
    }

    public String delegateFeedback(String text) throws IOException, InterruptedException {
        return feedBackDelegate.answer(text);
    }


}
