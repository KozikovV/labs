package zinchenko;

import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * User: zinchenko
 * Date: 11.01.14
 */
public class CustomPostgreDialect extends PostgreSQLDialect {

    public CustomPostgreDialect() {
        super();
        registerFunction("f", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "CREATE FUNCTION add(integer, integer) RETURNS integer\n" +
                "    AS 'select $1 + $2;'\n" +
                "    LANGUAGE SQL\n" +
                "    IMMUTABLE\n" +
                "    RETURNS NULL ON NULL INPUT;"));
    }

}
