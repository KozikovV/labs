package zinchenko.poi;

import ognl.NullHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class MyNullHandler implements NullHandler {
    @Override
    public Object nullMethodResult(Map context, Object target, String methodName, Object[] args) {
        try {
            return target.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return target;
    }

    @Override
    public Object nullPropertyValue(Map context, Object target, Object property) {
        Object newInstance = null;
        try {
            String fieldName = String.valueOf(property);
            Class<?> clazz = target.getClass().getDeclaredField(fieldName).getType();

            if (Collection.class.isAssignableFrom(clazz)) {
                newInstance = new ArrayList();
            }else{
                newInstance = clazz.newInstance();
            }

            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, newInstance);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newInstance;
    }

}
