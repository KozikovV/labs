package net.socket.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * User: zinchenko
 * Date: 9/6/13
 */
public class Client {
    public static void main(String[] args) throws IOException {

        String host = "10.10.3.281";
        int port = 7000;

        InetAddress inetAddress= InetAddress.getByName(host);

        Socket socket = new Socket(host, port);
    }
}
