package com.ernest.reefangel.service;

import com.ernest.reefangel.domain.RA;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import static com.ernest.reefangel.domain.Field.*;

/**
 * Created by ernest on 2017/01/14.
 */
@Service
@EnableScheduling
public class ReefAngelCloudScheduledService {

    private CommandService cloudCommandService;
    private RestTemplate restTemplate;
    private Logger log;
    private String id;

    @Autowired
    public ReefAngelCloudScheduledService(CommandService cloudCommandService, RestTemplate restTemplate, @Value("${reefangel.id}") String id) {
        this.cloudCommandService = cloudCommandService;
        this.restTemplate = restTemplate;
        this.id = id;
        this.log = Logger.getLogger(ReefAngelCloudScheduledService.class);
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void uploadStatusToPortal() throws URISyntaxException, IOException, InterruptedException {
        final RA ra = cloudCommandService.statusAll();
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(ID, id);
        params.add(T1, ra.getTemp1());
        params.add(T2, ra.getTemp2());
        params.add(T3, ra.getTemp3());
        params.add(R, ra.getR());
        params.add(AF, ra.getAf());
        params.add(ATOHIGH, ra.getAtoHIGH());
        params.add(ATOLOW, ra.getAtoLOW());
        params.add(BID, ra.getBid());
        params.add(EM, ra.getEm());
        params.add(EM1, ra.getEm1());
        params.add(PH, ra.getPh());
        params.add(PHE, ra.getPhe());
        params.add(SF, ra.getSf());
        params.add(REM, ra.getRem());
        params.add(RON, ra.getRelayOn());
        params.add(ROFF, ra.getRelayOFF());
        final URI uri = UriComponentsBuilder.fromHttpUrl("http://forum.reefangel.com/status/submitp.aspx").queryParams(params).build().toUri();
        final RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        final ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
        log.info(String.format("reefangel updated remote response :  %s %s", exchange.getStatusCode(), exchange.getBody()));
    }
}