package tasks.commons.net;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileFtpService {

    public String getContent() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("127.0.0.1", 21);
        boolean isConnected = ftpClient.isConnected();
        System.out.println("isConnected: " + isConnected);
        boolean isLogined = ftpClient.login("user", "password");
        System.out.println("isLogined: " + isLogined);
        int reply = ftpClient.getReplyCode();

        boolean isPositive = FTPReply.isPositiveCompletion(reply);
        System.out.println("isPositive: " + isLogined);

        File file = new File("file.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ftpClient.retrieveFile("file.txt", fileOutputStream);


        return "";
    }

}
