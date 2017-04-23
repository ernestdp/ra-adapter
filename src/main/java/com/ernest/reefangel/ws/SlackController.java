package com.ernest.reefangel.ws;

import com.ernest.reefangel.slack.SlackButtonService;
import com.ernest.reefangel.slack.SlackFileUploadService;
import com.ernest.reefangel.slack.SlackPushService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by ernest on 2017/04/18.
 */

@RestController
@RequestMapping(path = "/slack")
public class SlackController {

    private Logger log;
    private SlackPushService slackPushService;
    private SlackFileUploadService slackFileUploadService;
    private SlackButtonService slackButtonService;

    @Autowired
    public SlackController(SlackPushService slackPushService, SlackFileUploadService slackFileUploadService, SlackButtonService slackButtonService) {
        this.slackPushService = slackPushService;
        this.slackFileUploadService = slackFileUploadService;
        this.slackButtonService = slackButtonService;
        this.log = Logger.getLogger(SlackController.class);
    }


    @RequestMapping(path = "/send/{message}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.OK)
    public void send(@PathVariable String message) throws InterruptedException, IOException {
        slackPushService.send(message);
    }


    @RequestMapping(path = "/upload/{fileName}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.OK)
    public void upload(@PathVariable String fileName) throws InterruptedException, IOException {
        slackFileUploadService.sendFile(fileName);
    }


    @RequestMapping(path = "/buttons", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.OK)
    public void buttons() throws InterruptedException, IOException {
        slackButtonService.send("Lekker buttons!!");
    }
}
