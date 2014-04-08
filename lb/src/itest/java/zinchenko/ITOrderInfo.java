package java.zinchenko;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Rule;
import org.junit.runner.RunWith;
import zinchenko.Order;

import java.io.IOException;

//import static com.jayway.restassured.RestAssured.get;
//import static org.hamcrest.Matchers.equalTo;

/**
 * User: zinchenko
 * Date: 16.02.14
 */
@RunWith(HttpJUnitRunner.class)
public class ITOrderInfo {

    @Rule
    public Destination destination = new Destination("http://localhost:7070/");

    @Context
    private Response response;

    @HttpTest(method = Method.GET, path = "/rest/Order/all")
    public void testAll() {
//    org.objectweb.asm.MethodVisitor s;
//    get("/rest/Order/all");//.then().body("lotto.lottoId", equalTo(5));
    }

    @HttpTest(method = Method.GET, path = "/rest/Order/100")
    public void testGetById() throws IOException {
        String resp = response.getBody(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Order user = mapper.readValue(resp, Order.class);

//    org.objectweb.asm.MethodVisitor s;
//    get("/rest/Order/all");//.then().body("lotto.lottoId", equalTo(5));
    }

}
