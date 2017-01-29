package com.ernest.reefangel.service;

import com.ernest.reefangel.domain.Fields;
import com.ernest.reefangel.domain.RA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ernest on 2017/01/29.
 */
@Service
public class FeedbackService {

    private CloudCommandService cloudCommandService;

    @Autowired
    public FeedbackService(CloudCommandService cloudCommandService)
    {
        this.cloudCommandService=cloudCommandService;
    }

    public String delegateFeedback(String text) throws IOException, InterruptedException {
        StringBuilder response = new StringBuilder();
        if(text.toLowerCase().contains("status"))
        {
            final RA ra = cloudCommandService.statusAll();
            response.append("Here is a full status report.").append("\n")
            .append(Fields.PH).append(" ").append(ra.getPh()).append("\n")
            .append(Fields.T1).append(" ").append(ra.getTemp1()).append("\n")
            .append(Fields.T2).append(" ").append(ra.getTemp2()).append("\n")
            .append(Fields.T3).append(" ").append(ra.getTemp3()).append("\n")
            .append(Fields.ATOHIGH).append(" ").append(ra.getAtoHIGH()).append("\n");
        }
        return response.toString();
    }


}
