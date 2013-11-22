package test;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * User: zinchenko
 * Date: 10/7/13
 */
public class MainTest {

    @Test
    public void test() {
        Service service = Mockito.mock(Service.class, Mockito.RETURNS_MOCKS);
        service.getInternalService();

    }
}
