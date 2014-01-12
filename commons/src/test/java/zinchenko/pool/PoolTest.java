package zinchenko.pool;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.Before;
import org.junit.Test;
import zinchenko.pool.beans.Person;

public class PoolTest {

    ObjectPool<Person> pool;

    PersonFactory personFactory;



    @Before
    public void before() {
        personFactory = new PersonFactory();
        pool = new GenericObjectPool<Person>(personFactory);
    }

    @Test
    public void test() throws Exception {
        pool.addObject();
        Person person1 = pool.borrowObject();
        Person person2 = pool.borrowObject();
    }
}
