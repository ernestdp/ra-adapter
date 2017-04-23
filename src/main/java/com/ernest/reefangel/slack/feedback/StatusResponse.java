package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.Status;
import com.ernest.reefangel.domain.*;
import com.ernest.reefangel.service.CommandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.ernest.reefangel.domain.FeedbackOptions.status;


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
        return request.trim().toLowerCase().contains("#"+ status.name());
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
                    .append("`"+ Status.atoPretty(ra.getAtoHIGH())+"`")
                    .append("\n|#ATO Low = ")
                    .append("`"+ Status.atoPretty(ra.getAtoLOW())+"`");

            //RELAY stuff
            Relay relay = new Relay();
            relay.setRelayData(Short.valueOf(ra.getR()),Short.valueOf(ra.getRelayOn()),Short.valueOf(ra.getRelayOFF()));
            final Map<PortAlias, Port> ports = PortMappings.getPorts();
            builder.append("\n Relay : ");
            for (Map.Entry<PortAlias, Port> port : ports.entrySet()) {
                Port portValue = port.getValue();
                short portStatus = relay.getPortStatus(Short.valueOf(portValue.getNo()));
                boolean on = relay.isPortOn(Integer.valueOf(portValue.getNo()),true);

                builder.append("\n|#")
                .append(port.getKey().name())
                .append(" = ")
                .append("`"+Port.portPretty(portStatus)+"` ")
                .append(" | `"+Port.portONPretty(on)+"`");
            }
            return builder.toString();
        } catch (Exception e) {
            return "aaa I'm experiencing a communication error. Would you like to try again?"+e.getMessage();

        }
    }
}
