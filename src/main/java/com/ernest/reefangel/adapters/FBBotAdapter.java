package com.ernest.reefangel.adapters;

import co.aurasphere.botmill.fb.AbstractFbBot;
import co.aurasphere.botmill.fb.FbBotMillContext;
import co.aurasphere.botmill.fb.autoreply.MessageAutoReply;
import co.aurasphere.botmill.fb.event.message.MessageEvent;
import org.springframework.stereotype.Component;

/**
 * Created by ernest8 on 19/01/2017.
 */
@Component
public class FBBotAdapter extends AbstractFbBot {

        public void defineBehavior() {
            // Setting my tokens from Facebook (page token and validation token for webhook).
            FbBotMillContext.getInstance().setup("1851623115051543", "EAAaUCpoH0hcBAHyumkC0KqEHlKpohlPYTg1FE8YixytNZBrHH2URM94QwqbLCceVnYli4mJ6ZB6gzffds4uO3YuZCES2iyIrxOZC71FaeoecL6SgDUQRYSjdeEkZCtB5eJW4PXsB2t4Ld0lCS3fGxZC5J67P9uf6fC5WADHE5dIkejeZCqjZAO4gXZB549f4xkJgZD");

            // Defining a bot which will reply with "Hello World!" as soon as I write "Hi"
            addActionFrame(new MessageEvent("Hi"),new MessageAutoReply("Hello World!"));
        }
    
}
