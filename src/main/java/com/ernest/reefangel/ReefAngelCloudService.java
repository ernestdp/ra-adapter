package com.ernest.reefangel;

import com.ernest.reefangel.domain.Fields;
import com.ernest.reefangel.domain.RA;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.ernest.reefangel.domain.Fields.*;

/**
 * Created by ernest on 2017/01/14.
 */
@Service
@EnableScheduling
public class ReefAngelCloudService {

    private CommandService commandService;
    private RestTemplate restTemplate;
    private Logger log;

    @Autowired
    public ReefAngelCloudService(CommandService commandService,RestTemplate restTemplate)
    {
        this.commandService=commandService;
        this.restTemplate=restTemplate;
        this.log= Logger.getLogger(ReefAngelCloudService.class);
    }

    @Scheduled( cron = "0 0/60 * * * ?" )
    public void uploadStatusToPortal() throws URISyntaxException, IOException, InterruptedException {
        final RA ra = commandService.statusAll();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(ID,"linksar");
        params.add(T1,ra.getTemp1());
        params.add(T2,ra.getTemp2());
        params.add(T3,ra.getTemp3());
        params.add(R,ra.getR());
        params.add(AF,ra.getAf());
        params.add(ATOHIGH,ra.getAtoHIGH());
        params.add(ATOLOW,ra.getAtoLOW());
        params.add(BID,ra.getBid());
        params.add(EM,ra.getEm());
        params.add(EM1,ra.getEm1());
        params.add(PH,ra.getPh());
        params.add(PHE,ra.getPhe());
        params.add(SF,ra.getSf());
        params.add(REM,ra.getRem());
        params.add(RON,ra.getRelayOn());
        params.add(ROFF,ra.getRelayOFF());

        URI uri = UriComponentsBuilder.fromHttpUrl("http://forum.reefangel.com/status/submitp.aspx").queryParams(params).build().toUri();

        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET,uri);
        ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
        log.info(String.format("reefangel updated remote response :  %s %s", exchange.getStatusCode(), exchange.getBody()));
    }
}