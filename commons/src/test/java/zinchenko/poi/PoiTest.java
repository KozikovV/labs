package zinchenko.poi;

import com.opensymphony.xwork2.conversion.impl.InstantiatingNullHandler;
import com.opensymphony.xwork2.ognl.OgnlNullHandlerWrapper;
import com.opensymphony.xwork2.util.reflection.ReflectionContextState;
import ognl.Ognl;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PoiTest {


    @Test
    public void test() throws Exception{

        FileInputStream file = new FileInputStream(new File("C:\\file.xls"));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            String key = row.getCell(0).getRichStringCellValue().getString();
            String value = row.getCell(1).getRichStringCellValue().getString();
            System.out.println(key + " - " + value);
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~




    }

    @Test
    public void testInsertStringField() throws OgnlException {
        EmployerDetails employerDetails = new EmployerDetails();
        String key = "effectiveDate";
        Object expr = Ognl.parseExpression(key);
        Ognl.setValue(expr, employerDetails, "val");

        Assert.assertEquals("val", employerDetails.getEffectiveDate());
    }

    @Test
    public void testInsertStringFieldToNestedObject() throws OgnlException {
        EmployerDetails employerDetails = new EmployerDetails();
        AdditionalDetails additionalDetails = new AdditionalDetails();
        employerDetails.setAdditionalDetails(additionalDetails);

        String key = "additionalDetails.eligibleOpenEnroll";
        Object expr = Ognl.parseExpression(key);
        Ognl.setValue(expr, employerDetails, "val");

        Assert.assertEquals("val", employerDetails.getAdditionalDetails()
                .getEligibleOpenEnroll());
    }

    @Test
    public void testInsertStringFieldToNestedObjectIfTargetIsNull() throws Exception {
        EmployerDetails employerDetails = new EmployerDetails();

        String key = "additionalDetails.eligibleOpenEnroll";
        Object expr = Ognl.parseExpression(key);
        Map<String, Object> ognlContext = new HashMap<String, Object>();
        ognlContext.put(ReflectionContextState.CREATE_NULL_OBJECTS, true);

        InstantiatingNullHandler instantiatingNullHandler = new InstantiatingNullHandler();
        OgnlRuntime.setNullHandler(Object.class, new OgnlNullHandlerWrapper(instantiatingNullHandler));
//        ognlContext.put();

        Ognl.setValue(expr, ognlContext, employerDetails, "val");

        Assert.assertEquals("val", employerDetails.getAdditionalDetails()
                .getEligibleOpenEnroll());
    }

    @Test
    public void testInsertStringFieldToObjectFromList() throws OgnlException {
        EmployerDetails employerDetails = new EmployerDetails();
        List<Location> locations = new ArrayList<Location>();
        locations.add(new Location());
        employerDetails.setLocations(locations);

        String key = "locations[0].address";
        Object expr = Ognl.parseExpression(key);
        Ognl.setValue(expr, employerDetails, "val");

        Assert.assertEquals("val", employerDetails.getLocations().get(0)
                .getAddress());
    }

    @Test
    public void testInsertStringFieldToNestedObjectIfTargetIsNull2() throws OgnlException {
        EmployerDetails employerDetails = new EmployerDetails();
//        AdditionalDetails additionalDetails = new AdditionalDetails();
//        employerDetails.setAdditionalDetails(additionalDetails);

       // String key = "additionalDetails.eligibleOpenEnroll";
        //Object expr = Ognl.parseExpression(key);
        Map<String, Object> ognlContext = new HashMap<String, Object>();
//        NullHandler customHander = new MyNullHandler();
        OgnlRuntime.setNullHandler(Object.class, new MyNullHandler());
        OgnlRuntime.setPropertyAccessor(Object.class, new MyObjectAccessor());
        OgnlRuntime.setPropertyAccessor(List.class, new ListPropAccesor());

//        Ognl.setValue(Ognl.parseExpression("additionalDetails.eligibleOpenEnroll"),
//                employerDetails, "val");
//        Assert.assertEquals("val", employerDetails.getAdditionalDetails()
//                .getEligibleOpenEnroll());


        Ognl.setValue("locations[0].address", employerDetails, "Address");

        Ognl.setValue("effectiveDate", employerDetails, "123");

    }

    private List<?> stringList = new ArrayList<String>();
    private List<?> integerList = new ArrayList<Integer>();

    @Test
    public void test0000() throws Exception {
        Field stringListField = this.getClass().getDeclaredField("stringList");
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.

        Field integerListField = this.getClass().getDeclaredField("integerList");
        ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
        Class<?> integerListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
        System.out.println(integerListClass); // class java.lang.Integer.
    }






}
