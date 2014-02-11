package zinchenko.poi;

import ognl.ListPropertyAccessor;
import ognl.OgnlException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public class ListPropAccesor extends ListPropertyAccessor {

    private List<Object> targetList;

    @Override
    public Object getProperty(Map context, Object target, Object name) throws OgnlException {
        try {
            if (name instanceof Number) {
                System.out.println("target = " + target + " name = " + name);
                targetList = (List) target;
                int currentIndex = Integer.parseInt(String.valueOf(name));
                int listSize = targetList.size();
                if (currentIndex >= listSize) {
                    Class<?> lastBeanClass = (Class<?>) context.get(Constants.LAST_BEAN_CLASS_ACCESSED);
                    String lastProperty = (String) context.get(Constants.LAST_BEAN_PROPERTY_ACCESSED);
                    Field field = lastBeanClass.getDeclaredField(lastProperty);
                    ParameterizedType parameterizedType = (ParameterizedType)field.getGenericType();
                    Class<?> listContentClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    System.out.println(listContentClass);
                    targetList.add(currentIndex, listContentClass.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.getProperty(context, target, name);
    }
}
