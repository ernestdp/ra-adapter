package com.ernest.reefangel.adapters;

import gnu.io.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

import java.util.Enumeration;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class provides the utilities to read the data exchanged via USB port.
 */
@Component
public class USBCommAdapter implements SerialPortEventListener {

    private InputStream inputStream;
    private OutputStream outputStream;
    private SerialPort serialPort;
    public LinkedBlockingQueue<Byte> receivedBytes;
    private CommPortIdentifier portId = null;
    private Logger log;
    private final int portTimeout;
    private final String port;
    private final int baudRate;

    /**
     * Builds a new manager for the communication via USB port.
     *
     * @throws IOException if an error occurred during the opening of the USB port
     */
    @Autowired
    public USBCommAdapter(@Value("${usb.port}") String port, @Value("${usb.port.timeout}") int portTimeout, @Value("${usb.baudrate}") int baudRate) throws IOException {
        this.portTimeout = portTimeout;
        this.port = port;
        this.baudRate = baudRate;
        log = Logger.getLogger(USBCommAdapter.class);
        receivedBytes = new LinkedBlockingQueue<Byte>(100000);
        identifyPort();
    }

    public void identifyPort() throws IOException {
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
        boolean portFound = false;
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(port)) {
                    log.info("Found port: " + port);
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
            log.info("USB port opening...");
            serialPort = (SerialPort) portId.open(this.getClass().getName(), portTimeout);
            log.info("USB port opened");
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);

            Thread.sleep(1000);
            serialPort.setSerialPortParams(baudRate,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    public void closeUSB() {
        try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            final String error = String.format("Cannot close streams for port : %s", port);
            log.error(error + e.getMessage());
            throw new RuntimeException(error, e);
        }
    }

    /**
     * Listener for USB events
     *
     * @param event new event occurred on the USB port
     */
    public void serialEvent(SerialPortEvent event) {
        if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            byte received = -1;

            do {
                try {
                    received = (byte) inputStream.read();

                } catch (IOException e) {
                    log.error("Error reading USB:" + e.getMessage());
                }

                synchronized (receivedBytes) {
                    try {
                        receivedBytes.add(received);
                    } catch (IllegalStateException ew) {
                        log.error(ew.getMessage());
                        receivedBytes.poll();
                        receivedBytes.add(received);
                    }
                }
            } while (received != -1);
        }
    }

    public void write(byte[] buffer) {
        try {
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException e) {
            final String error = String.format("Unable to write to %s", port);
            log.error("Cannot write:" + e.getMessage());
            throw new RuntimeException(error, e);
        }
    }
}