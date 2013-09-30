package net.socket;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * User: zinchenko
 * Date: 9/6/13
 */
public class Server {
    public static void main(String[] args) throws IOException {

        int port = 7000;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.accept();



        System.out.println("client arrived");
    }
}
