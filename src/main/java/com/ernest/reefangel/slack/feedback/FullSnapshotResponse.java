package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.PortAlias;
import com.ernest.reefangel.service.CommandService;
import com.ernest.reefangel.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class FullSnapshotResponse extends FeedBackResponse {

    @Autowired
    PhotoService photoService;

    public FullSnapshotResponse(CommandService commandService){
        super(commandService);
    }


    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("#pic");
    }

    @Override
    String defineResponseMessage(String request) {
        try {
            commandService.start(PortAlias.sumplight.name());
            Thread.sleep(10000);
            photoService.snapshot();
            Thread.sleep(10000);
            commandService.stop(PortAlias.sumplight.name());
        } catch (Exception e) {
            log.error(e);
            return String.format("Unable to take a capture image. %s. Try again buy typing 'snapshot'.", e.getMessage());
        }
        return "Tada!!";
    }
}
