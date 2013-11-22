package hibernate.dao;

import hibernate.domain.Domain;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: zinchenko
 * Date: 10/2/13
 */

@Repository
public class DomainDao extends AbstractDao {

    @Transactional
    public List<Domain> findAll(){
        return getCurrentSession().createCriteria(Domain.class).list();
    }

    @Transactional
    public void create(Domain domain){
        getCurrentSession().save(domain);
    }

    @Transactional
    public void criteria(){
        Session session = getCurrentSession();


    }

}
