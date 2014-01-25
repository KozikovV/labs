package zinchenko;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * User: zinchenko
 * Date: 25.01.14
 */
public class CamelFileCopier implements FileCopier{

    @Override
    public void copy() throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:data/inbox?noop=true")
                        .to("file:data/outbox");
            }
        });
        camelContext.start();
        Thread.sleep(1000);
        camelContext.stop();
    }
}
