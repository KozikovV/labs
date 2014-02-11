package zinchenko.poi;

import ognl.ObjectPropertyAccessor;
import ognl.OgnlException;

import java.util.Map;

public class MyObjectAccessor extends ObjectPropertyAccessor {

    @Override
    public Object getProperty(Map context, Object target, Object oname) throws OgnlException {
        Object obj = super.getProperty(context, target, oname);
        context.put(Constants.LAST_BEAN_CLASS_ACCESSED, target.getClass());
        context.put(Constants.LAST_BEAN_PROPERTY_ACCESSED, oname.toString());
        return obj;
    }
}
