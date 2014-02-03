package zinchenko.usingbeans;

/**
 * User: zinchenko
 * Date: 02.02.14
 */
public interface Service {

    zinchenko.usingbeans.domains.Person find(Long id);

    zinchenko.usingbeans.domains.Person findByName(String name);

}
