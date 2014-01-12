package zinchenko.lang;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import zinchenko.lang.beans.Person;

import java.util.Collections;
import java.util.List;

/**
 * User: zinchenko
 * Date: 04.01.14
 */
public class Builder {

    private static final Log LOG = LogFactory.getLog(Builder.class);

    public void compareBuilder(List<Person> persons){
        Collections.sort(persons);
    }
}
