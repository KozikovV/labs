package zinchenko;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import zinchenko.engl.bean.Category;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    @Test
    public void testTest() throws JsonProcessingException {
        zinchenko.engl.bean.Test test = new zinchenko.engl.bean.Test();
        test.setId(12L);
        test.setName("t_name_12");
        Category category = new Category();
        category.setId(1111L);
        category.setName("c_name_1111");
        List tests = new ArrayList();
        tests.add(test);
        category.setTests(tests);
        test.setCategory(category);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(test));
        System.out.println(objectMapper.writeValueAsString(category));
    }


}
