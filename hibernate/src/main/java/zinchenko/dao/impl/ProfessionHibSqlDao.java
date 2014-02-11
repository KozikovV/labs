package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.ProfessionDao;
import zinchenko.domain.Profession;

import java.util.List;

/**
 * User: zinchenko
 * Date: 19.01.14
 */
public class ProfessionHibSqlDao implements ProfessionDao {

    private SessionFactory sessionFactory;

    @Override
    public List<Profession> findAllGetCurrentSes() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Profession> findAllOpenSes() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Profession find(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long save(Profession profession) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
        //To change body of implemented methods use File | Settings | File Templates.
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
