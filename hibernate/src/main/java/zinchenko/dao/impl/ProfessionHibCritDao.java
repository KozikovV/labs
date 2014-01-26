package zinchenko.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import zinchenko.dao.ProfessionDao;
import zinchenko.domain.Car;
import zinchenko.domain.Profession;

import java.util.List;

public class ProfessionHibCritDao implements ProfessionDao {

    private static final Log LOG = LogFactory.getLog(ProfessionHibCritDao.class);

    private SessionFactory sessionFactory;

    private Interceptor professionInterceptor;

    @Override
    public List<Profession> findAllGetCurrentSes() {
        return sessionFactory.getCurrentSession().createCriteria(Profession.class).list();
    }

    @Override
    public List<Profession> findAllOpenSes() {
        Session session = null;
        List<Profession> professions = null;
        try {
            session = sessionFactory.openSession();
            professions = session.createCriteria(Profession.class).list();
        }catch (Exception e){
            LOG.error(e);
        }finally {
            if(session != null){
                session.close();
            }
        }
        return professions;
    }

    @Override
    public Profession find(Long id) {
        return (Profession) sessionFactory.getCurrentSession().get(Profession.class, id);
    }

    @Override
    public Long save(Profession profession) {
        return (Long) sessionFactory.getCurrentSession().save(profession);
    }

    @Override
    public void saveBatchNaive(List<Profession> professions) {
        Session session = sessionFactory.getCurrentSession();
        for (Profession profession : professions) {
            session.save(profession);
        }
    }

    @Override
    public void saveBatch(List<Profession> professions) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Profession profession) {
        sessionFactory.getCurrentSession().delete(profession);
    }

    @Override
    public Long saveWithInterceptor(Profession profession) {
        Session session = null;
        try{
            session = sessionFactory.openSession(professionInterceptor);
            session.save(profession);
            session.flush();
        }catch (Exception e){
            LOG.error("Exception is occured when profession was saving", e);
            throw new RuntimeException(e);
        }finally {
            if(session != null){
                session.close();
            }
        }

        return 0L;
    }

    @Override
    public Profession load(Long id) {
        return (Profession) sessionFactory.getCurrentSession().load(Profession.class, id);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Interceptor getProfessionInterceptor() {
        return professionInterceptor;
    }

    public void setProfessionInterceptor(Interceptor professionInterceptor) {
        this.professionInterceptor = professionInterceptor;
    }
}
