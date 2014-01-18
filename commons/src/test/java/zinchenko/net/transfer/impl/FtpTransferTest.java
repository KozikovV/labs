package zinchenko.net.transfer.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockftpserver.fake.FakeFtpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zinchenko.net.FtpConnectInfo;
import zinchenko.net.transfer.FtpTransfer;

/**
 * User: zinchenko
 * Date: 18.01.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("ftpTransferTest.xml")
public class FtpTransferTest {

    FtpTransfer ftpTransfer;

    @Autowired
    @Qualifier("sourceConnectInfo")
    FtpConnectInfo sourceConnectInfo;

    @Autowired
    @Qualifier("destinationConnectInfo")
    FtpConnectInfo destinationConnectInfo;

    @Autowired
    @Qualifier("sourceFtpServer")
    FakeFtpServer sourceFtpServer;

    @Autowired
    @Qualifier("destinationFtpServer")
    FakeFtpServer destinationFtpServer;

    @Before
    public void before(){
        sourceFtpServer.start();
        destinationFtpServer.start();

        ftpTransfer = new CommonsFtpTransfer();
        ftpTransfer.setSourceConnectInfo(sourceConnectInfo);
        ftpTransfer.setDestinationConnectInfo(destinationConnectInfo);
        ftpTransfer.init();
    }

    @After
    public void after(){
        ftpTransfer.destroy();
    }

    @Test
    public void test() throws Exception{
        ftpTransfer.transf();
    }

}
