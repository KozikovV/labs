package zinchenko.usingbeans;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * User: zinchenko
 * Date: 02.02.14
 */
public class UsingBeanRouteBuilder extends RouteBuilder {

    private Service service;

    @Override
    public void configure() throws Exception {
        from("direct:getBeanById")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Long id = exchange.getIn().getBody(Long.class);
                        zinchenko.usingbeans.domains.Person bean = service.find(id);
                        exchange.getOut().setBody(bean);
                    }
                });
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
