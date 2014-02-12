package zinchenko.jms.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    static int orderSequence = 1;

    @Autowired
    OrderSender orderSender;

    public void sendOrder(int customerId, double price)
    {
        Order order = new Order(orderSequence, 2, price, "ordercd"+ orderSequence++);
        orderSender.sendOrder(order);
    }

    public OrderSender getOrderSender() {
        return orderSender;
    }

    public void setOrderSender(OrderSender orderSender) {
        this.orderSender = orderSender;
    }

}
