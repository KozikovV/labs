package tasks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tasks.commons.net.FileFtpService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/tasks/test.xml")
public class FileFtpServiceTest {

    FileFtpService fileFtpService = new FileFtpService();

//    @Autowired
//    FakeFtpServer fakeFtpServer;

    @Before
    public void before() {
/*        FakeFtpServer fakeFtpServer = new FakeFtpServer();
        fakeFtpServer.setServerControlPort(21);
        fakeFtpServer.setSystemName("UNIX");
        fakeFtpServer.addUserAccount(new UserAccount("user", "password", "/"));

        FileSystem fileSystem = new UnixFakeFileSystem();
        fileSystem.add(new DirectoryEntry("/"));
        fileSystem.add(new FileEntry("/file.txt", "abcdef 1234567890"));
//        fileSystem.add(new FileEntry("c:\\data\\run.exe"));
        fakeFtpServer.setFileSystem(fileSystem);

        fakeFtpServer.start();
        int serverControlPort = fakeFtpServer.getServerControlPort();
        System.out.println("serverControlPort: " + serverControlPort);*/
    }

    @Test
    public void testGetContent() throws Exception {
        String s = fileFtpService.getContent();

    }
}
