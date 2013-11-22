package test;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * User: zinchenko
 * Date: 10/7/13
 */
public class Main {
    public static void main(String[] args) {
        Service service = mock(Service.class, RETURNS_DEEP_STUBS);
        String answer = doReturn("aaa").when(service).getInternalService().doSomething();
    }
}
