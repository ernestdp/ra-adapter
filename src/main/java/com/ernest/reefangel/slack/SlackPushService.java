package com.ernest.reefangel.slack;

import com.ernest.reefangel.ws.CloudCommandController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.ernest.reefangel.domain.Field.ID;

/**
 * Created by ernest on 2017/04/18.
 */
@Service
public class SlackPushService {

    private RestTemplate slackApiTemplate;

    @Value("${slackChatPostUrl}")
    private String chatPostUrl;

    @Value("${slackBotToken}")
    private String token;

    @Value("${generalChannel}")
    private String channel;

    Logger log;

    @Autowired
    public SlackPushService(RestTemplate slackApiTemplate)
    {
        this.slackApiTemplate=slackApiTemplate;
        this.log = Logger.getLogger(SlackPushService.class);

    }

    public void send(String message)
    {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token",token);
        params.add("channel", channel);
        params.add("text",message);
        final URI uri = UriComponentsBuilder.fromHttpUrl(chatPostUrl).queryParams(params).build().toUri();
        final RequestEntity requestEntity = new RequestEntity(HttpMethod.POST, uri);
        final ResponseEntity<String> exchange = slackApiTemplate.exchange(requestEntity, String.class);
        log.info(String.format("Sent notification to slack : %s", message));
    }

}
