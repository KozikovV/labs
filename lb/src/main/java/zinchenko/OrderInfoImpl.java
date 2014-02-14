package zinchenko;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


public class OrderInfoImpl implements OrderInfo {

    public OrderInfoImpl() {
    }

    @GET
    @Produces("application/xml")
    @Path("/{orderId}")
    @Override
    public Order getOrder(@PathParam("orderId") int orderId) {
        Order order = new Order();
        order.setName("name-" + orderId);
        return order;
    }

    @GET
    @Path("/all")
    @Override
    public void m() {
        System.out.println("m()");
    }
}
