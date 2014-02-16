package zinchenko;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/Order")
@Produces("application/json")
public interface OrderInfo {

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Order get(@PathParam("id") int id);

    @GET
    @Produces("application/json")
    @Path("/all")
    public List<Order> getAll();

}
