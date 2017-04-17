package com.ernest.reefangel.slack.feedback;

import com.ernest.reefangel.domain.Port;
import com.ernest.reefangel.domain.PortMappings;
import com.ernest.reefangel.service.CommandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by ernest8 on 06/02/2017.
 */
@Service
@Primary
public class StartResponse extends FeedBackResponse {

    public StartResponse(CommandService commandService) {
        super(commandService);
    }

    @Override
    boolean isCondition(String request) {
        return request.trim().toLowerCase().contains("start");
    }

    @Override
    String defineResponseMessage(String request) {
        Map<String, Port> ports = PortMappings.getPorts();
        String label=null;
        for (String s : ports.keySet()) {
            if(request.toLowerCase().trim().contains(s))
            {
                label=s;
                Port port = ports.get(label);
                try {
                    commandService.command("/r"+port.getNo()+"1");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "ok started "+label;
    }
}
