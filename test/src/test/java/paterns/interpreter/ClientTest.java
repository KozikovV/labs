package paterns.interpreter;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: zinchenko
 * Date: 29.12.13
 */
public class ClientTest {

    Client client = new Client();

    @Test
    public void testExpression1(){
        Integer a = 3;
        Integer b = 3;
        Integer c = 2;

        Integer result = client.expression1(a, b, c);
        Assert.assertEquals((Object) 3, result);
    }


}
