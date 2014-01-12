package zinchenko.dao;

import zinchenko.domain.Person;

import java.util.List;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
public interface PersonDao {

    public List<Person> findAll();

}
