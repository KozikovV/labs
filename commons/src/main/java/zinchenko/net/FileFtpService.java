package zinchenko.net;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.text.MessageFormat;

public class FileFtpService {

    private static final Log LOG = LogFactory.getLog(FileFtpService.class);

    private String host;

    private int port;

    private FTPClient ftpClient;

    private boolean success;

    public void init(String user, String password) {
        try {
            ftpClient = new FTPClient();
            LOG.debug(MessageFormat
                    .format("Connecting to host \"{0}\" and port \"{1}\"", host, port));
            ftpClient.connect(host, port);
            LOG.debug(MessageFormat.format("Is connected: {0}", ftpClient.isConnected()));
            boolean isLogined = ftpClient.login(user, password);
            LOG.debug(MessageFormat.format("Is logined: {0}", isLogined));

            int reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)){
                LOG.error("FTP server refused connection.");
                ftpClient.disconnect();
                throw new IOException("FTP server refused connection.");
            }
            LOG.debug("Connected successfully");
            success = true;
        } catch (IOException e){
            LOG.error(MessageFormat.format("Error when \"{0}\" was trying to connect to {1}:{2}", user, host, port));
            throw new RuntimeException(e);
        }
    }

    public void destroy(){
        if(ftpClient.isConnected()){
            try {
                LOG.debug("Disconnecting...");
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            LOG.debug("Disconnected");
        }
    }

    public String getContent() throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        ftpClient.retrieveFile("/file.txt", outputStream);
        return outputStream.toString();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


}
