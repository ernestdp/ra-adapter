package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.db.entity.Record;
import com.ernest.reefangel.service.CommandService;
import com.ernest.reefangel.service.PhotoService;
import com.ernest.reefangel.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class SnapshotResponse extends FeedBackResponse {

    @Autowired
    PhotoService photoService;

    public SnapshotResponse(CommandService commandService){
        super(commandService);
    }


    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("photo")
                ||request.trim().toLowerCase().contains("pic")
                ||request.toLowerCase().trim().contains("image")
                ||request.trim().toLowerCase().contains("snapshot")
                ||request.trim().toLowerCase().contains("see");
    }

    @Override
    String defineResponseMessage(String request) {
        try {
            photoService.snapshot();
        } catch (IOException e) {
            log.error(e);
            return String.format("Unable to take a capture image. %s. Try again buy typing 'snapshot'.", e);
        }
        return "See attached file/s.";
    }
}
