package zinchenko;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

/**
 * User: zinchenko
 * Date: 25.01.14
 */
public class MyFtpSever {

    public void m(){
        Main main = new Main();
        main.addRouteBuilder(new MyRouteBuilder());
    }

}

class MyRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
