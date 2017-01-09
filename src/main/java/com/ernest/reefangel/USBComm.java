package com.ernest.reefangel;

import gnu.io.*;
import org.springframework.stereotype.Component;

import java.io.*;

import java.util.Enumeration;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class provides the utilities to read the data exchanged via USB port.
 */
@Component
public class USBComm implements SerialPortEventListener {

    /**
     * Stream for the storage of incoming data
     */
    private InputStream inputStream;
    /**
     * Stream for the dispatching of data
     */
    private OutputStream outputStream;
    /**
     * Timeout of the USB port
     */
    private final int PORT_TIMEOUT = 2000;
    /**
     * Representation of the serial port used for the communication
     */
    private SerialPort serialPort;
    /**
     * Buffer that stores the received bytes from the media
     */
    protected LinkedBlockingQueue<Byte> receivedBytes;


    String port = "/dev/ttyUSB0"; //place the right COM port here, OS dependent

    CommPortIdentifier portId = null;


    /**
     * Builds a new manager for the communication via USB port.
     * @exception IOException if an error occurred during the opening of the USB port
     */
    public USBComm() throws IOException {
        receivedBytes = new LinkedBlockingQueue<Byte>(100000);
        identifyPort();
    }

    public void identifyPort() throws IOException {
        //Check that the USB port exists and is recognized:
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
        boolean portFound = false;
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(portId.getName());
                if (portId.getName().equals(port)) {
                    System.out.println("Found port: " + port);
                    portFound = true;
                    break;
                }
            }
        }
        if (!portFound)
            throw new IOException("port " + port + " not found.");
        connect();
    }

    public void connect() throws IOException {
        try {
            System.out.println("USB port opening...");
            serialPort = (SerialPort) portId.open(this.getClass().getName(), PORT_TIMEOUT);
            System.out.println("USB port opened");
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);

            Thread.sleep(1000);
            serialPort.setSerialPortParams(57600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            System.out.println("setted SerialPortParams");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    public void closeUSB(){
        //close the streams for serial port:
        try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.err.println("Cannot close streams:" + e.getMessage());
        }
    }

    /**
     * Listener for USB events
     *
     * @param event new event occurred on the USB port
     */
    public void serialEvent(SerialPortEvent event){
        if(event.getEventType()==SerialPortEvent.DATA_AVAILABLE){
                byte received = -1;
                do {
                    try {
                        received = (byte)inputStream.read();
                    } catch (IOException e) {
                        System.err.println("Error reading USB:" + e.getMessage());
                    }

                    synchronized (receivedBytes) {
                        try {
                            receivedBytes.add(received);
                        } catch (IllegalStateException ew) {
                            System.err.println(ew.getMessage());
                            receivedBytes.poll();
                            receivedBytes.add(received);
                        }
                    }
                } while(received != -1);
        }
    }

    protected void write(byte[] buffer){
        try {
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Cannot write:" + e.getMessage());
        }
    }
}