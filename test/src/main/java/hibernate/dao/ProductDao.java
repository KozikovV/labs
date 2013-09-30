package hibernate.dao;

import hibernate.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: zinchenko
 * Date: 9/30/13
 */

@Repository
public class ProductDao extends AbstractDao{

    @Transactional
    public List<Product> findAll(){
        return getCurrentSession().createCriteria(Product.class).list();
    }

}
