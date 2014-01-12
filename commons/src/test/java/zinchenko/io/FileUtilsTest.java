package tasks.commons.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;

import static org.junit.Assert.*;

/**
 * User: zinchenko
 * Date: 05.01.14
 */
public class FileUtilsTest {

    private static final Log LOG = LogFactory.getLog(FileUtilsTest.class);

    @Test
    public void testForceDeleteOnExit() throws IOException {
        File dir = Files.createTempDirectory("tempRemoverDir_").toFile();
        File file = new File(dir, "file.txt");
        file.createNewFile();
        FileUtils.forceDeleteOnExit(dir);
    }

    @Test
    public void testCleanDirectory() throws IOException {
        File dir = Files.createTempDirectory("tempDir_").toFile();
        dir.deleteOnExit();
        File file = new File(dir, "file.txt");
        file.createNewFile();

        String fullNameOfFile = file.getAbsolutePath();
        assertTrue(new File(fullNameOfFile).exists());
        FileUtils.cleanDirectory(dir);
        assertFalse(new File(fullNameOfFile).exists());
    }

    @Test
    public void testReadWriteStringToFile() throws IOException {
        File dir = Files.createTempDirectory("tempDir_").toFile();
        FileUtils.forceDeleteOnExit(dir);
        dir.deleteOnExit();
        File file = new File(dir, "file.txt");
        file.createNewFile();
        file.deleteOnExit();
        LOG.trace(file.getAbsolutePath());

        String stringToSave = "string to save";
        FileUtils.writeStringToFile(file, stringToSave);
        String result = FileUtils.readFileToString(file);

        LOG.trace(MessageFormat.format("Text from file: {0}", result));

        assertEquals(stringToSave, result);
    }

    @Test
    public void testByteCountToDisplaySize() {
        long size = 10;
        String displaySize = FileUtils.byteCountToDisplaySize(size);
        LOG.trace(MessageFormat.format("Size is: {0} and display is: {1}", size, displaySize));

        long size2 = 1024;
        String displaySize2 = FileUtils.byteCountToDisplaySize(size2);
        LOG.trace(MessageFormat.format("Size is: {0} and display is: {1}", size2, displaySize2));

        long size3 = 999999999;
        String displaySize3 = FileUtils.byteCountToDisplaySize(size3);
        LOG.trace(MessageFormat.format("Size is: {0} and display is: {1}", size3, displaySize3));
    }



}
