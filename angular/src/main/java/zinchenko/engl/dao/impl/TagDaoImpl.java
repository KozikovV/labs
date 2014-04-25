package zinchenko.engl.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zinchenko.engl.bean.Tag;
import zinchenko.engl.dao.TagDao;

import java.io.Serializable;
import java.util.List;

@Repository
public class TagDaoImpl implements TagDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Tag> findAll() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Tag.class).list();
    }

    @Override
    public Long create(Tag tag) {
        return (Long) sessionFactory.getCurrentSession()
                .save(tag);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
