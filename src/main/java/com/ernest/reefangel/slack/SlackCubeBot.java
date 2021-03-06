package com.ernest.reefangel.slack;

import com.ernest.reefangel.slack.feedback.FeedBackDelegate;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;


/**
 * Created by ernest8 on 24/01/2017.
 */
@Component
public class SlackCubeBot extends Bot {

    @Value("${slackBotToken}")
    private String token;

    private FeedBackDelegate feedBackDelegate;

    private SlackPushService slackPushService;


    @Autowired
    public SlackCubeBot(FeedBackDelegate feedBackDelegate,SlackPushService slackPushService){
        this.feedBackDelegate = feedBackDelegate;
        this.slackPushService = slackPushService;
        slackPushService.send("I'm back. Something restarted me..");
    }

    @Controller(events = {EventType.DIRECT_MESSAGE,EventType.DIRECT_MENTION,EventType.MESSAGE})
    public void onReceiveMessage(WebSocketSession session, Event event) throws IOException, InterruptedException {
            final String feedback = feedBackDelegate.answer(event.getText());
            reply(session, event, new Message(feedback));
        }

    @Override
    public String getSlackToken() {
        return token;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }
}
