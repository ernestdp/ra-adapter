package com.ernest.reefangel;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Enumeration;

@Component
public class USBPort implements SerialPortEventListener {

    SerialPort serialPort;
    private byte[] readBuff;
    private int numOfBytes;
    private String serialResponse = "";

    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = {
            "/dev/tty.usbserial-A9007UX1", // Mac OS X
            "/dev/ttyACM0", // Raspberry Pi
            "/dev/ttyUSB0", // Linux
            "COM3", // Windows
    };
    /**
     * A BufferedReader which will be fed by a InputStreamReader
     * converting the bytes into characters
     * making the displayed results codepage independent
     */
    private InputStream input;
    /** The output stream to the port */
    private OutputStream output;
    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 57600;
    private final int COM_TIMEOUT=1000;
    private String response;

    public USBPort() {
        //System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            throw new RuntimeException("Could not find COM port.");
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            serialPort.enableReceiveTimeout(COM_TIMEOUT);




            // open the streams
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();


            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This should be called when you stop using the port.
     * This will prevent port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent evnt) {
        switch(evnt.getEventType())
        {
            case SerialPortEvent.DATA_AVAILABLE :
                readBuff = new byte[1024];

                try
                {
                    if(input.available() > 0)
                    {
                        numOfBytes = input.read(readBuff);
                        System.out.println("Received " + numOfBytes + " Bytes");
                        serialResponse = new String(new String(readBuff,0,numOfBytes));
                        System.out.println(serialResponse);
                    }
                }
                catch(IOException ex)
                {
                    System.out.println("IO Exceptioon reading serial response: " + ex.getMessage());
                    numOfBytes = 0;
                    serialResponse = "";
                }
                break;
        }
    }


    public synchronized String write(String command)
    {
        if(serialPort != null){
            System.out.printf("command %s%n", command);
            try {
                output.write(command.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getResponse();
    }

    public String getResponse() {
        return serialResponse;
    }
}