package com.ernest.reefangel.slack;

import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;


/**
 * Created by ernest8 on 24/01/2017.
 */
@Component
public class SlackCubeBot extends Bot {

    @Value("${slackBotToken}")
    private String token;



    @Controller(events = EventType.USER_TYPING)
    public void onReceiveDirectMessage(WebSocketSession session, Event event) {
        final String text = event.getText();
        System.out.println(text);
        reply(session, event, new Message("Ja ok lekker."));
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
