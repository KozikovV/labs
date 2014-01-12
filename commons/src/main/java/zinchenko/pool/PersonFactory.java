package zinchenko.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import zinchenko.pool.beans.Person;

public class PersonFactory extends BasePooledObjectFactory<Person> {

    @Override
    public Person create() throws Exception {
        return new Person();
    }

    @Override
    public PooledObject<Person> wrap(Person person) {
        return new DefaultPooledObject<Person>(person);
    }

}
