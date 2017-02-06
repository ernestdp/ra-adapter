package com.ernest.reefangel.service;

import com.ernest.reefangel.adapters.USBCommAdapter;
import com.ernest.reefangel.domain.RA;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ernest on 2017/01/07.
 */
@Service
public class CloudCommandService {

    private USBCommAdapter usbCommAdapter;
    private Logger log;


    @Autowired
    public CloudCommandService(USBCommAdapter usbCommAdapter) {
        this.usbCommAdapter = usbCommAdapter;
        this.log = Logger.getLogger(CloudCommandService.class);
    }


    public RA statusAll() throws IOException, InterruptedException {
        usbCommAdapter.receivedBytes.clear();
        usbCommAdapter.write(new String("GET /sa ").getBytes("UTF-8"));
        final byte[] bytes = new byte[1024];
        int i = 0;
        while (true) {
            final Byte take = usbCommAdapter.receivedBytes.take();
            bytes[++i] = take;
            String s = new String(bytes);
            if (s.trim().contains("<RA>") && s.trim().contains(("</RA>"))) {
                String substring = s.substring(s.indexOf("<RA>"), (s.lastIndexOf("</RA>")) + 5);
                log.info(substring);
                ObjectMapper mapper = new XmlMapper();
                final RA ra = mapper.readValue(substring, RA.class);
                return ra;
            }
        }
    }

    public String command(String command) throws IOException, InterruptedException {
        log.info(String.format("About to submit : %s to device.", command));
        usbCommAdapter.receivedBytes.clear();
        usbCommAdapter.write(new String("GET /" + command + " ").getBytes("UTF-8"));
        final byte[] bytes = new byte[1024];
        int i = 0;
        while (true) {
            final Byte take = usbCommAdapter.receivedBytes.take();
            bytes[++i] = take;
            String s = new String(bytes);
            if (s.trim().contains("<RA>") && s.trim().contains("</RA>")) {
                return s.substring(s.indexOf("<RA>"), (s.lastIndexOf("</RA>")) + 5);
            } else if (s.trim().contains("<V>") && s.trim().contains("</V>")) {
                return s.substring(s.indexOf("<V>"), (s.lastIndexOf("</V>")) + 4);
            } else if (s.trim().contains("<MODE>") && s.trim().contains("</MODE>")) {
                return s.substring(s.indexOf("<MODE>"), (s.lastIndexOf("</MODE>")) + 7);
            } else if (s.trim().contains("<D>") && s.trim().contains("</D>")) {
                return s.substring(s.indexOf("<D>"), (s.lastIndexOf("</D>")) + 4);
            } else if (s.trim().contains("<M>") && s.trim().contains("</M>")) {
                return s.substring(s.indexOf("<M>"), (s.lastIndexOf("</M>")) + 4);
            } else if (s.trim().contains("<object") && s.trim().contains("</object>")) {
                return s.substring(s.indexOf("<object"), (s.lastIndexOf("</object>")) + 9);
            } else if (s.trim().contains("<MEM>") && s.trim().contains("</MEM>")) {
                return s.substring(s.indexOf("<MEM>"), (s.lastIndexOf("</MEM>")) + 6);
            }
        }
    }
}
