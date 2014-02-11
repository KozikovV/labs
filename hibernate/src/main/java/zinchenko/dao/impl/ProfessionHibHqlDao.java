package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.ProfessionDao;
import zinchenko.domain.Car;
import zinchenko.domain.Profession;

import java.util.List;

public class ProfessionHibHqlDao implements ProfessionDao{

    protected SessionFactory sessionFactory;

    @Override
    public List<Profession> findAllGetCurrentSes() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Profession as profession")
                .list();
    }

    @Override
    public List<Profession> findAllOpenSes() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Profession find(Long id) {
        return (Profession) sessionFactory.getCurrentSession()
                .createQuery("from Profession as prof where prof.id = :id")
                .setLong("id", id)
                .uniqueResult();
    }

    @Override
    public Long save(Profession profession) {
        return (Long) sessionFactory.getCurrentSession().save(profession);
    }

    @Override
    public void saveBatchNaive(List<Profession> professions) {
        //To change body of implemented methods use File | Settings | File Templates.
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Profession load(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void growUpAllLevels() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
