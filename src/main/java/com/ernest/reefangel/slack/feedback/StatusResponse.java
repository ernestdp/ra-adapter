package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.StatusUtil;
import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.service.CommandService;
import com.inamik.text.tables.Cell;
import com.inamik.text.tables.GridTable;
import com.inamik.text.tables.SimpleTable;
import com.inamik.text.tables.grid.Border;
import com.inamik.text.tables.grid.Util;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.inamik.text.tables.Cell.Functions.*;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class StatusResponse extends FeedBackResponse {


    public StatusResponse(CommandService commandService) {
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("status");
    }

    @Override
    String defineResponseMessage(String request) {
        try {
            RA ra = commandService.statusAll();
            StringBuilder builder = new StringBuilder();
            builder.append("|#PH = ")
                    .append("`"+ra.getPh()+"`")
                    .append("\n|#Temprature = ")
                    .append("`"+ra.getTemp1()+"`")
                    .append("\n|#ATO High = ")
                    .append("`"+StatusUtil.atoPretty(ra.getAtoHIGH())+"`")
                    .append("\n|#ATO Low = ")
                    .append("`"+StatusUtil.atoPretty(ra.getAtoLOW())+"`")
                    .append("\n|#Relay on = ")
                    .append("`"+ra.getR()+"`")
                    .append("\n|#Relay Off = ")
                    .append("`"+ra.getRelayOFF()+"`");
            return builder.toString();
        } catch (Exception e) {
            return "aaa I'm experiencing a communication error. Would you like to try again?"+e.getMessage();

        }
    }
}
