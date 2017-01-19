package com.ernest.reefangel.adapters;

import co.aurasphere.botmill.fb.AbstractFbBot;
import co.aurasphere.botmill.fb.FbBotMillContext;
import co.aurasphere.botmill.fb.autoreply.MessageAutoReply;
import co.aurasphere.botmill.fb.event.message.MessageEvent;

/**
 * Created by ernest8 on 19/01/2017.
 */
public class FBBotAdapter extends AbstractFbBot {

        public void defineBehavior() {
            // Setting my tokens from Facebook (page token and validation token for webhook).
            FbBotMillContext.getInstance().setup("myFacebookPageToken", "myFacebookWebhookValidationToken");

            // Defining a bot which will reply with "Hello World!" as soon as I write "Hi"
            addActionFrame(new MessageEvent("Hi"),new MessageAutoReply("Hello World!"));
        }
    
}
