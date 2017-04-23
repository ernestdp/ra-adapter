package com.ernest.reefangel.slack;

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

/**
 * Created by ernest on 2017/04/18.
 */
@Service
public class SlackButtonService {

    private RestTemplate slackApiTemplate;

    @Value("${slackChatPostUrl}")
    private String chatPostUrl;

    @Value("${slackBotToken}")
    private String token;

    @Value("${generalChannel}")
    private String channel;

    Logger log;

    @Autowired
    public SlackButtonService(RestTemplate slackApiTemplate)
    {
        this.slackApiTemplate=slackApiTemplate;
        this.log = Logger.getLogger(SlackButtonService.class);

    }


    String buttons = "[\n" +
            "        {\n" +
            "            \"text\": \"Choose a game to play\",\n" +
            "            \"fallback\": \"You are unable to choose a game\",\n" +
            "            \"callback_id\": \"wopr_game\",\n" +
            "            \"color\": \"#3AA3E3\",\n" +
            "            \"attachment_type\": \"default\",\n" +
            "            \"actions\": [\n" +
            "                {\n" +
            "                    \"name\": \"game\",\n" +
            "                    \"text\": \"Chess\",\n" +
            "                    \"type\": \"button\",\n" +
            "                    \"value\": \"chess\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"game\",\n" +
            "                    \"text\": \"Falken's Maze\",\n" +
            "                    \"type\": \"button\",\n" +
            "                    \"value\": \"maze\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"game\",\n" +
            "                    \"text\": \"Thermonuclear War\",\n" +
            "                    \"style\": \"danger\",\n" +
            "                    \"type\": \"button\",\n" +
            "                    \"value\": \"war\",\n" +
            "                    \"confirm\": {\n" +
            "                        \"title\": \"Are you sure?\",\n" +
            "                        \"text\": \"Wouldn't you prefer a good game of chess?\",\n" +
            "                        \"ok_text\": \"Yes\",\n" +
            "                        \"dismiss_text\": \"No\"\n" +
            "                    }\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]";

    public void send(String message)
    {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token",token);
        params.add("channel", channel);
        params.add("text",message);
        params.add("attachments",buttons);
        final URI uri = UriComponentsBuilder.fromHttpUrl(chatPostUrl).queryParams(params).build().toUri();
        final RequestEntity requestEntity = new RequestEntity(HttpMethod.POST, uri);
        final ResponseEntity<String> exchange = slackApiTemplate.exchange(requestEntity, String.class);
        log.info(String.format("Sent buttons notification to slack : %s", message));
    }

}
