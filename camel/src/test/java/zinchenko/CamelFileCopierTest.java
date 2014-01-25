package zinchenko;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * User: zinchenko
 * Date: 25.01.14
 */
public class CamelFileCopierTest {

    FileCopier fileCopier = new CamelFileCopier();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCopy() throws Exception {
        fileCopier.copy();
    }
}
