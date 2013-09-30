package common.lang;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.reflect.FieldUtils;

/**
 * User: zinchenko
 * Date: 9/23/13
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IllegalAccessException {
        Bean bean = new Bean();
       String field = (String) FieldUtils.readField(bean, "field", true);


    }

    public static void function(Bean bean){
        Validate.notNull(bean, "The bean is null!");

        System.out.println("end");
    }


}

class Bean {
    private String name;

    private String field = "asd";

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
