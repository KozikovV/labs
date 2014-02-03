package zinchenko.filecopy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zinchenko.filecopy.impl.CamelFileCopier;

/**
 * User: zinchenko
 * Date: 02.02.14
 */
public class FileCopierTest {

    FileCopier fileCopier;

    @Before
    public void setUp() throws Exception {
        fileCopier = new CamelFileCopier();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCopyFiles() throws Exception {
        fileCopier.copyFiles("", "");
    }

}
