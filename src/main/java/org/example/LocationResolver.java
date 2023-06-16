package org.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocationResolver {
    public void displayLocalClient() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("IP Address: " + localHost.getHostAddress());
            System.out.println("Host Name: " + localHost.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}