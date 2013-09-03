package laba10;

import interfaces.junit.JunitTester;
import junit.framework.TestSuite;

public class JunitTesterImpl implements JunitTester {

    @Override
    public TestSuite suite() {
        TestSuite suite = new TestSuite(ArrayCollectionImplTest.class);
        return suite;
    }

}
