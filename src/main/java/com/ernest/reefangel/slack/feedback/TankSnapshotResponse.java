package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.db.entity.Record;
import com.ernest.reefangel.domain.Command;
import com.ernest.reefangel.domain.FeedbackOptions;
import com.ernest.reefangel.domain.PortAlias;
import com.ernest.reefangel.service.CommandService;
import com.ernest.reefangel.service.PhotoService;
import com.ernest.reefangel.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.ernest.reefangel.domain.FeedbackOptions.coralpic;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class TankSnapshotResponse extends FeedBackResponse {

    @Autowired
    PhotoService photoService;

    public TankSnapshotResponse(CommandService commandService){
        super(commandService);
    }


    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("#"+ coralpic.name());
    }

    @Override
    String defineResponseMessage(String request) {
        try {
            commandService.start(PortAlias.sumplight.name());
            commandService.command(Command.mf.name());
            Thread.sleep(60000);//wait for water to clear
            photoService.snapshot();
            Thread.sleep(30000);//wait for water to clear
            commandService.command(Command.bp.name());
        } catch (Exception e) {
            log.error(e);
            return String.format("Unable to take a capture image or executing water change command. %s. Try again buy typing 'snapshot'.", e.getMessage());
        }
        return "See attached file/s.";
    }
}
