package com.ernest.reefangel.slack;

import com.sun.javafx.fxml.builder.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.Date;


/**
 * Created by ernest on 2017/03/21.
 */
@Service
public class SlackFileUploadService {


    @Value("${slackAccountToken}")
    private String userToken;

    @Value("${generalChannel}")
    private String channel;

    private RestTemplate restTemplate;

    @Autowired
    public SlackFileUploadService(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }

    public void sendFile(String fileName)
    {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new FileSystemResource(fileName));
        map.add("token", userToken);
        map.add("channels",channel);
        HttpEntity<MultiValueMap<String, Object>> multiValueMapHttpEntity = new HttpEntity<>(map, headers);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange("https://slack.com/api/files.upload", HttpMethod.POST, multiValueMapHttpEntity, String.class);
            System.out.println(exchange.getBody());
        }catch(HttpClientErrorException e) {
            System.out.println("Document not found! Status code " + e.getStatusCode());
        }
    }
}
