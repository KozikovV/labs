package zinchenko.jms.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class OrderSender {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendOrder(final Order order){
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setInt("orderId", order.getOrderId());
                mapMessage.setInt("customerId", order.getCustomerId());
                mapMessage.setDouble("price", order.getPrice());
                mapMessage.setString("orderCode", order.getOrderCode());
                return mapMessage;
            }
        });
        System.out.println("Order sent - id: "+ order.getOrderId());
    }

    public Order receive(){
        Message message = jmsTemplate.receive();
//        message.g
        return null;
    }

}
