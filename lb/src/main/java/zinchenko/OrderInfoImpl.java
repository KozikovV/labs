package zinchenko;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;


public class OrderInfoImpl implements OrderInfo {

    public OrderInfoImpl() {
    }

    @Override
    public Order get(int id) {
        Order order = new Order();
        order.setName("name-get-" + id);
        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<Order>();
        Order order = new Order();
        order.setName("nn");
        orders.add(order);
        return orders;
    }
}
