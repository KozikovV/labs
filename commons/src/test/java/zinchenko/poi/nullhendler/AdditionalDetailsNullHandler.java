package zinchenko.poi.nullhendler;

import ognl.NullHandler;
import zinchenko.poi.AdditionalDetails;

import java.util.Map;

public class AdditionalDetailsNullHandler implements NullHandler {

    @Override
    public Object nullMethodResult(Map context, Object target, String methodName, Object[] args) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object nullPropertyValue(Map context, Object target, Object property) {
        return new AdditionalDetails();
    }
}
