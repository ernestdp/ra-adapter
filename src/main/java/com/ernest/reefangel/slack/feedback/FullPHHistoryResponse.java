package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.db.entity.Record;
import com.ernest.reefangel.service.CommandService;
import com.ernest.reefangel.service.PdfService;
import com.ernest.reefangel.service.RecordService;
import com.ernest.reefangel.slack.SlackFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class FullPHHistoryResponse extends FeedBackResponse {

    @Autowired
    RecordService recordService;

    @Autowired
    PdfService pdfService;

    @Autowired
    SlackFileUploadService fileUploadService;

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    public FullPHHistoryResponse(CommandService commandService){
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("#ph");
    }

    @Override
    String defineResponseMessage(String request) {
        try {
            List<Record> records = recordService.retrieveTopPh();

        if(records==null || records.size()<1)
        {
            return "Sorry could not find any ph readings.";
        }

        StringBuffer buffer = new StringBuffer();
            for(Record record: records)
            {
            buffer.append(record.getPh());
            buffer.append(" | ");
            buffer.append(record.getCaptureDate().format(formatter));
            buffer.append("| \n");
            }
            final String pdf = pdfService.createPdf(buffer.toString());
            fileUploadService.sendFile(pdf);
            return "Tada, readings attached!";
        } catch (Exception e) {
            return "Unable to collect and export  readings." +e.getMessage();
        }
    }
}
