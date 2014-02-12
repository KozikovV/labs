package zinchenko.jms.commons;

import org.apache.commons.messenger.Messenger;
import org.apache.commons.messenger.MessengerManager;
import org.junit.Before;
import org.junit.Test;

import javax.jms.Destination;
import javax.jms.TextMessage;

public class UTest {

    @Before
    public void setUp() {

    }

    @Test
    public void test() throws Exception {
        System.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        System.setProperty("java.naming.provider.url", "tcp://localhost:61616");//"localhost:1099");

        Messenger messenger = MessengerManager.get("queue");
        Destination destination = messenger.getDestination("REQUEST.BUILD");

        TextMessage message = messenger.createTextMessage("this is some text");
        messenger.send(destination, message);
    }

}
