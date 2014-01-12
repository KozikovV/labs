package zinchenko.lang;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import zinchenko.lang.beans.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zinchenko
 * Date: 04.01.14
 */
public class BuilderTest {

    private static final Log LOG = LogFactory.getLog(BuilderTest.class);

    Builder builder = new Builder();

    @Test
    public void testCompareBuilder() throws Exception {
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person(){{
            setName("A");
            setAge(2);
        }});
        persons.add(new Person(){{
            setName("C");
            setAge(1);
        }});
        persons.add(new Person(){{
            setName("B");
            setAge(1);
        }});
        builder.compareBuilder(persons);
        Assert.assertEquals((Object) 1, persons.get(0).getAge());
        Assert.assertEquals("B", persons.get(0).getName());

        Assert.assertEquals((Object) 1, persons.get(1).getAge());
        Assert.assertEquals("C", persons.get(1).getName());

        Assert.assertEquals((Object) 2, persons.get(2).getAge());
        Assert.assertEquals("A", persons.get(2).getName());

        LOG.debug(persons);
    }
}
