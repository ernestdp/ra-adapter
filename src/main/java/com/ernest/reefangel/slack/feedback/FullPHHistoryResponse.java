package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.db.entity.Record;
import com.ernest.reefangel.service.CommandService;
import com.ernest.reefangel.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class FullPHHistoryResponse extends FeedBackResponse {

    @Autowired
    RecordService recordService;

    public FullPHHistoryResponse(CommandService commandService){
        super(commandService);
    }


    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("ph history");
    }

    @Override
    String defineResponseMessage(String request) {
        List<Record> records = recordService.retrieveAll();
        if(records.size()<1)
        {
            return "Sorry could not find any ph readings.";
        }
        StringBuffer buffer = new StringBuffer();
        for(Record record: records)
        {
            buffer.append("|");
            buffer.append(record.getPh());
            buffer.append("|");
            buffer.append(record.getCaptureDate());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
