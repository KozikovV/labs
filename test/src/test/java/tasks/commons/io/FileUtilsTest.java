package tasks.commons.io;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * User: zinchenko
 * Date: 05.01.14
 */
public class FileUtilsTest {

    private static final Log LOG = LogFactory.getLog(FileUtilsTest.class);

    @Test
    public void test() throws IOException {
        File dir = Files.createTempDirectory("tempDir_").toFile();
        dir.deleteOnExit();
        File file = new File(dir, "file.txt");
        file.createNewFile();
        file.deleteOnExit();
        LOG.trace(file.getAbsolutePath());

    }

}
