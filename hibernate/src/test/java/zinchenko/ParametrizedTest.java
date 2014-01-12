package zinchenko;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
@RunWith(Parameterized.class)
public class ParametrizedTest {

    Parametrized parametrized;

    Integer expectedResult;

    Integer numberFirst;

    Integer numberSecond;

    public ParametrizedTest(int expectedResult, int numberFirst,
                            int numberSecond, Parametrized parametrized) {
        this.expectedResult = expectedResult;
        this.numberFirst = numberFirst;
        this.numberSecond = numberSecond;
        this.parametrized = parametrized;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { 3, 1, 2, new SumParam()},
                { 7, 5, 2, new SumParam()},
                { -1, 1, 2, new MinusParam()},
                { 3, 5, 2, new MinusParam()}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testAction() {
        System.out.println("Number testAction(): " + numberFirst + "  for test: " + this + " and class: " + parametrized);

        Integer result = parametrized.action(numberFirst, numberSecond);
        Assert.assertEquals(expectedResult, result);
    }

}
