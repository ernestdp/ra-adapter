package com.ernest.reefangel;

import com.ernest.reefangel.domain.RA;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ernest on 2017/01/07.
 */
@Service
public class CommandService {

    private USBComm usbComm;


    @Autowired
    public CommandService(USBComm usbComm)
    {
        this.usbComm=usbComm;
    }

    public void reboot()
    {
        usbComm.write(new String("GET /boot ").getBytes());
        usbComm.receivedBytes.clear();

    }

    public void feed()
    {
        usbComm.write(new String("GET /mf ").getBytes());
        usbComm.receivedBytes.clear();
    }

    public RA statusAll() {
        usbComm.receivedBytes.clear();
        byte[] bytes = new byte[1024];

        try {
            usbComm.write(new String("GET /sa ").getBytes("UTF-8"));
        int i=0;
        while(true) {
            final Byte take = usbComm.receivedBytes.take();
            bytes[++i]=take;
            String s = new String(bytes);
            if(s.trim().contains("<RA>") && s.trim().contains(("</RA>"))){
                String substring = s.substring(s.indexOf("<RA>"), (s.lastIndexOf("</RA>"))+5);
                System.out.println(substring);
                ObjectMapper mapper = new XmlMapper();
              //  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                //        .configure(DeserializationFeature.EAGER_DESERIALIZER_FETCH,false);
                RA ra = mapper.readValue(substring, RA.class);
                return ra;
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void waterChange() {
        usbComm.write(new String("GET /mw ").getBytes());
        usbComm.receivedBytes.clear();
    }

    public void clear() {
        usbComm.write(new String("GET /mt ").getBytes());//ATO
        usbComm.write(new String("GET /mo ").getBytes());//OVERHEAT
        usbComm.write(new String("GET /ml ").getBytes());//LEAK
        usbComm.write(new String("GET /mt ").getBytes());
        usbComm.receivedBytes.clear();

    }
}
