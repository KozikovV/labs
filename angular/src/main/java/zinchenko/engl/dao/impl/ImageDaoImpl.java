package zinchenko.engl.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zinchenko.engl.bean.Image;
import zinchenko.engl.dao.ImageDao;

import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Image> findAll() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Image.class).list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
