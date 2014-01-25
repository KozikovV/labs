package zinchenko.dao.impl;

import zinchenko.dao.AbstractDao;
import zinchenko.dao.HouseDao;
import zinchenko.domain.House;

import java.util.List;

/**
 * User: zinchenko
 * Date: 25.01.14
 */
public class HouseHibSqlDao extends AbstractDao implements HouseDao {
    @Override
    public List<House> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public House find(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void save() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(House house) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
