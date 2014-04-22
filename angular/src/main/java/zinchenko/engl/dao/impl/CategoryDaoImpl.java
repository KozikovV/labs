package zinchenko.engl.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zinchenko.engl.bean.Category;
import zinchenko.engl.dao.CategoryDao;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select distinct c from Category c where c.parentCategory.id = null").list();
    }

    @Override
    public Category find(Long id) {
        return (Category) sessionFactory.getCurrentSession().get(Category.class, id);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
