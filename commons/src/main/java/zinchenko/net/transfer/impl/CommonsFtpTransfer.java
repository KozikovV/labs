package zinchenko.net.transfer.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import zinchenko.net.FtpConnectInfo;
import zinchenko.net.transfer.FtpTransfer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;

/**
 * User: zinchenko
 * Date: 18.01.14
 */
public class CommonsFtpTransfer implements FtpTransfer {

    private static final Log LOG = LogFactory.getLog(CommonsFtpTransfer.class);

    FtpConnectInfo sourceConnectInfo;

    FtpConnectInfo destinationConnectInfo;

    private FTPClient sourceFtpClient;

    private FTPClient destinationFtpClient;

    public CommonsFtpTransfer() {
        sourceFtpClient = new FTPClient();
        destinationFtpClient = new FTPClient();
    }

    public void init(){
        initFtp(sourceFtpClient, sourceConnectInfo);
        initFtp(destinationFtpClient, destinationConnectInfo);
    }

    private void initFtp(FTPClient ftpClient, FtpConnectInfo ftpConnectInfo) {
        try {
            LOG.debug(MessageFormat
                    .format("Connecting to host \"{0}\" and port \"{1}\"",
                            ftpConnectInfo.getHost(), ftpConnectInfo.getPort()));
            ftpClient.connect(ftpConnectInfo.getHost(), ftpConnectInfo.getPort());
            LOG.debug(MessageFormat.format("Is connected: {0}", ftpClient.isConnected()));
            boolean isLogined = ftpClient.login(ftpConnectInfo.getUser(), ftpConnectInfo.getPassword());
            LOG.debug(MessageFormat.format("Is logined: {0}", isLogined));

            int reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)){
                LOG.error("FTP server refused connection.");
                ftpClient.disconnect();
                throw new IOException("FTP server refused connection.");
            }
            LOG.debug("Connected successfully");
        } catch (IOException e){
            LOG.error(MessageFormat.format("Error when \"{0}\" was trying to connect to {1}:{2}",
                    ftpConnectInfo.getUser(),
                    ftpConnectInfo.getHost(),
                    ftpConnectInfo.getPort()));
            throw new RuntimeException(e);
        }
    }

    public void destroy(){
        destroyFtp(sourceFtpClient);
        destroyFtp(destinationFtpClient);
    }

    private void destroyFtp(FTPClient ftpClient){
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


    @Override
    public void setSourceConnectInfo(FtpConnectInfo sourceConnectInfo) {
        this.sourceConnectInfo = sourceConnectInfo;
    }

    @Override
    public void setDestinationConnectInfo(FtpConnectInfo destinationConnectInfo) {
        this.destinationConnectInfo = destinationConnectInfo;
    }

    @Override
    public void transf() throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        sourceFtpClient.retrieveFile("/file.txt", outputStream);
        LOG.debug("Content of /file.txt: " + outputStream.toString());


    }
}
