package zinchenko.filecopy.impl;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * User: zinchenko
 * Date: 02.02.14
 */
public class TransformProcessor implements Processor {

//    private static final Log LOG = LogFactory.getLog(TransformProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().getBody();
    }
}
