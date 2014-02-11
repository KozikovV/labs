package zinchenko.usingbeans;

/**
 * User: zinchenko
 * Date: 02.02.14
 */
public interface Service {

    Person find(Long id);

    Person findByName(String name);

}
