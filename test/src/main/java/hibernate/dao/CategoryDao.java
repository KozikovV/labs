package hibernate.dao;

import hibernate.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * User: zinchenko
 * Date: 9/30/13
 */

@Repository
public class CategoryDao extends AbstractDao {

    @Transactional
    public List<Category> findAll(){
        return getCurrentSession().createCriteria(Category.class).list();
    }

    @Transactional
    public void create(Category category){
        Serializable serializable = getCurrentSession().save(category);
    }


}
