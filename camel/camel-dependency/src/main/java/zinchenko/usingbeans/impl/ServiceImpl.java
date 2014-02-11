package zinchenko.usingbeans.impl;

import zinchenko.usingbeans.Person;
import zinchenko.usingbeans.Service;

/**
 * User: zinchenko
 * Date: 02.02.14
 */
public class ServiceImpl implements Service{

    @Override
    public Person find(Long id) {
        Person bean = new Person();
        bean.setId(id);
        bean.setName("name-" + id);
        return bean;
    }

    @Override
    public Person findByName(String name) {
        Person bean = new Person();
        bean.setId(Long.valueOf(name.length()));
        bean.setName("name-" + name);
        return bean;
    }

}
