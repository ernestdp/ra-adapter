package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.FeedbackOptions;
import com.ernest.reefangel.domain.PortAlias;
import com.ernest.reefangel.service.CommandService;
import com.ernest.reefangel.service.PhotoService;
import com.ernest.reefangel.service.UsbService;
import com.ernest.reefangel.slack.SlackPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import static com.ernest.reefangel.domain.FeedbackOptions.usb;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class DeviceListResponse extends FeedBackResponse {

    @Autowired
    UsbService usbService;

    @Autowired
    SlackPushService slackPushService;

    public DeviceListResponse(CommandService commandService){
        super(commandService);
    }


    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("#"+ usb.name());
    }

    @Override
    String defineResponseMessage(String request) {
        try {
            String devices = usbService.list();
            slackPushService.send(devices);
        } catch (Exception e) {
            log.error(e);
            return String.format("Unable to query os device list. %s. Try again.", e.getMessage());
        }
        return "Tada!!";
    }
}
