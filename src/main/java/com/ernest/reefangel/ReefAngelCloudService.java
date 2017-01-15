package com.ernest.reefangel;

import com.ernest.reefangel.domain.RA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

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
        this.log= Logger.getLogger(ReefAngelCloudService.class.getName());
    }

    //t1=0&t2=0&t3=0&ph=142&id=linksar&em=192&em1=0&rem=0&bid=1&key=&ddns=&af=0&sf=0&atohigh=0&atolow=0&r=6&ron=0&roff=255&phe=0&wl=0
    @Scheduled( cron = "0 0/1 * * * ?" )
    public void uploadStatusToPortal() throws URISyntaxException {
        final RA ra = commandService.statusAll();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id","linksar");
        params.add("t1",ra.getTemp1());

        URI uri = UriComponentsBuilder.fromHttpUrl("http://forum.reefangel.com/status/submitp.aspx").queryParams(params).build().toUri();

        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET,uri);
        ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
        log.info(String.format("reefangel updated remote response :  %s %s", exchange.getStatusCode(), exchange.getBody()));
    }
}