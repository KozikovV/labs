package zinchenko.dao;

import zinchenko.domain.MultiId;
import zinchenko.domain.Person;

import java.util.List;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
public interface PersonDao {

    public List<Person> findAll();

    public Person find(MultiId multiId);

    public MultiId save(Person person);

    public void delete(Person person);

}
