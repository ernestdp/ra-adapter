package com.ernest.reefangel;

import com.ernest.reefangel.domain.RA;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by ernest on 2017/01/07.
 */
@Service
public class CommandService {

    private USBCommAdapter usbCommAdapter;
    private Logger log;


    @Autowired
    public CommandService(USBCommAdapter usbCommAdapter)
    {
        this.usbCommAdapter = usbCommAdapter;
        this.log= Logger.getLogger(CommandService.class);
    }

    public void reboot()
    {
        usbCommAdapter.write(new String("GET /boot ").getBytes());
        usbCommAdapter.receivedBytes.clear();

    }

    public void feed()
    {
        usbCommAdapter.write(new String("GET /mf ").getBytes());
        usbCommAdapter.receivedBytes.clear();
    }

    public RA statusAll() throws IOException, InterruptedException {
        usbCommAdapter.receivedBytes.clear();
        usbCommAdapter.write(new String("GET /sa ").getBytes("UTF-8"));
        byte[] bytes = new byte[1024];
        int i=0;
        while(true) {
            final Byte take = usbCommAdapter.receivedBytes.take();
            bytes[++i]=take;
            String s = new String(bytes);
            if(s.trim().contains("<RA>") && s.trim().contains(("</RA>"))){
                String substring = s.substring(s.indexOf("<RA>"), (s.lastIndexOf("</RA>"))+5);
                System.out.println(substring);
                ObjectMapper mapper = new XmlMapper();
                final RA ra = mapper.readValue(substring, RA.class);
                return ra;
            }
        }
    }

    public void waterChange() {
        usbCommAdapter.write(new String("GET /mw ").getBytes());
        usbCommAdapter.receivedBytes.clear();
    }

    public void clear() {
        usbCommAdapter.write(new String("GET /mt ").getBytes());//ATO
        usbCommAdapter.write(new String("GET /mo ").getBytes());//OVERHEAT
        usbCommAdapter.write(new String("GET /ml ").getBytes());//LEAK
        usbCommAdapter.write(new String("GET /mt ").getBytes());
        usbCommAdapter.receivedBytes.clear();

    }

    public String command(String command) throws IOException, InterruptedException {
        log.info(String.format("About to submit : %s to device.", command));
        usbCommAdapter.receivedBytes.clear();
        usbCommAdapter.write(new String("GET /"+command+" ").getBytes("UTF-8"));
        byte[] bytes = new byte[1024];
        int i=0;
        while(true) {
            final Byte take = usbCommAdapter.receivedBytes.take();
            bytes[++i] = take;

            String s = new String(bytes);
            System.out.println(s);

            if ( s.trim().contains("</RA>") || s.trim().contains("</V>") || s.trim().contains("</MODE>")) {
                System.out.println(s);
                return s;
            }
        }
    }
}
