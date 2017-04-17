package com.ernest.reefangel.slack;

import com.sun.javafx.fxml.builder.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.util.Date;


/**
 * Created by ernest on 2017/03/21.
 */
@Component
public class SlackFileUploader {


    @Value("${slackAccountToken}")
    private String userToken;

    private RestTemplate restTemplate;

    @Autowired
    public SlackFileUploader(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }

    public void sendFile()
    {

        File file = new File("");
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", userToken);
        params.add("filetype","jpg");
        params.add("title","pic"+new Date());

        final URI uri = UriComponentsBuilder.fromHttpUrl("https://slack.com/api/files.upload").queryParams(params).build().toUri();

        final RequestEntity requestEntity = RequestEntity.post(uri).body(file);

        final ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
        System.out.println(exchange.getBody());

    }

    
}
