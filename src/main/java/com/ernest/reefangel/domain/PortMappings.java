package com.ernest.reefangel.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ernest on 2017/03/25.
 */
public class PortMappings {

    static Map<PortAlias,Port> ports = new HashMap<>();


    public static Map<PortAlias,Port> getPorts()
    {
        if(ports.size()<=0)
        {
            buildPorts();
        }
        return ports;
    }

    public static Port getPort(String label)
    {
        if(ports.size()<=0)
        {
            buildPorts();
        }
        return ports.get(label);
    }

/*
#define Wave      Port1
#define ChillerP  Port2
#define Skimmer   Port3
#define SumpLight Port4
#define Heater    Port5
#define ReturnP   Port6
#define Chiller   Port7
#define ATU       Port8
 */
    public  static void buildPorts()
    {
        ports.put(PortAlias.wavemaker,new Port("1",null));
        ports.put(PortAlias.chillerpump,new Port("2",null));
        ports.put(PortAlias.skimmer,new Port("3",null));
        ports.put(PortAlias.sumplight,new Port("4",null));
        ports.put(PortAlias.heater,new Port("5",null));
        ports.put(PortAlias.returnpump,new Port("6",null));
        ports.put(PortAlias.chiller,new Port("7",null));
        ports.put(PortAlias.atu,new Port("8",null));
    }

}
