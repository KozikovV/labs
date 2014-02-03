package zinchenko.usingbeans.impl;

import zinchenko.usingbeans.Service;

/**
 * User: zinchenko
 * Date: 02.02.14
 */
public class ServiceImpl implements Service{

    @Override
    public zinchenko.usingbeans.domains.Person find(Long id) {
        zinchenko.usingbeans.domains.Person bean = new zinchenko.usingbeans.domains.Person();
        bean.setId(id);
        bean.setName("name-" + id);
        return bean;
    }

    @Override
    public zinchenko.usingbeans.domains.Person findByName(String name) {
        zinchenko.usingbeans.domains.Person bean = new zinchenko.usingbeans.domains.Person();
        bean.setId(Long.valueOf(name.length()));
        bean.setName("name-" + name);
        return bean;
    }

}
