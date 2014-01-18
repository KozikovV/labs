package zinchenko.net.transfer;

import zinchenko.net.FtpConnectInfo;

import java.io.IOException;

/**
 * User: zinchenko
 * Date: 18.01.14
 */
public interface FtpTransfer {

    public void setSourceConnectInfo(FtpConnectInfo sourceConnectInfo);

    public void setDestinationConnectInfo(FtpConnectInfo destinationConnectInfo);

    public void init();

    public void transf() throws IOException;

    public void destroy();

}
