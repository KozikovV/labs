package zinchenko.transaction.oneOrchestrated;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zinchenko.transaction.oneOrchestrated.bean.Order;

import javax.transaction.Transaction;
import javax.transaction.Transactional;

@Repository("orderDao")
public class OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Long save(Order order){
        return (Long) sessionFactory.getCurrentSession().save(order);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
