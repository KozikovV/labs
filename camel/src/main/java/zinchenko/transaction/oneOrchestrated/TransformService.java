package zinchenko.transaction.oneOrchestrated;


import org.springframework.stereotype.Service;
import zinchenko.transaction.oneOrchestrated.bean.Order;
import zinchenko.transaction.oneOrchestrated.bean.OrderBean;
import zinchenko.transaction.oneOrchestrated.bean.User;

import javax.jws.soap.SOAPBinding;

@Service("transformService")
public class TransformService {

    public Order transform(OrderBean orderBean){
        Order order = new Order();
        order.setName(orderBean.getName());
        order.setPrice(orderBean.getPrice());
//        User user = new User();
//        user.setFirstName(orderBean.getFirstName());
//        user.setLastName(orderBean.getLastName());
//        order.setUser(user);
        return order;
    }

}
