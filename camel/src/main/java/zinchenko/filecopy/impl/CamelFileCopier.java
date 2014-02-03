package zinchenko.filecopy.impl;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import zinchenko.filecopy.FileCopier;

/**
 * User: zinchenko
 * Date: 02.02.14
 */
public class CamelFileCopier implements FileCopier {

    @Override
    public void copyFiles(String from, String to) {
        try {
            CamelContext camelContext = new DefaultCamelContext();
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file:data/inbox?noop=true")
                            .process(new TransformProcessor())
                            .to("file:data/outbox");
                }
            });
            camelContext.start();
            Thread.sleep(1000);
            camelContext.stop();
        } catch (Exception e) {
            throw new RuntimeException("Failed file copy ", e);
        }
    }

}
