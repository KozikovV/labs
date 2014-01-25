package zinchenko.dao;

import zinchenko.domain.House;

import java.util.List;

/**
 * User: zinchenko
 * Date: 25.01.14
 */
public interface HouseDao {

    public List<House> findAll();

    public House find(Long id);

    public void save();

    public void delete(House house);

}
