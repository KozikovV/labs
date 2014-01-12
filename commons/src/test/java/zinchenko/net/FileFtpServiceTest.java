package zinchenko.net;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockftpserver.fake.FakeFtpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("configtest.xml")
public class FileFtpServiceTest {

    public static final int PORT = 7070;

    public static final String HOST = "localhost";

    public static final String USER = "user";

    public static final String PASSWORD = "password";

    FileFtpService fileFtpService;

    @Autowired
    FakeFtpServer fakeFtpServer;

    @Before
    public void before() throws IOException {
        fakeFtpServer.start();

        fileFtpService = new FileFtpService();
        fileFtpService.setHost(HOST);
        fileFtpService.setPort(PORT);
        fileFtpService.init(USER, PASSWORD);
    }

    @After
    public void after() {
        fileFtpService.destroy();
    }

    @Test
    public void testGetContent() throws Exception {
        String s = fileFtpService.getContent();
        Assert.assertEquals("content of file.txt", s);
    }
}
