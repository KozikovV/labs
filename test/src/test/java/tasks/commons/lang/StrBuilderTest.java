package tasks.commons.lang;

import org.apache.commons.lang.text.StrBuilder;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: zinchenko
 * Date: 04.01.14
 */
public class StrBuilderTest {

    @Test
    public void test() {
        StrBuilder builder = new StrBuilder("str");
        assertEquals("str", builder.toString());

        builder.append("ing");
        assertEquals("string", builder.toString());

        builder.appendAll(new String[]{" (it", " is ", "from", " array)"});
        assertEquals("string (it is from array)", builder.toString());

        builder.appendWithSeparators(new String[]{"(it", "is", "from", "array)"}, "|");
        assertEquals("string (it is from array)(it|is|from|array)", builder.toString());

        builder.clear();
        assertEquals("", builder.toString());

        builder.appendPadding(3, 'd');
        assertEquals("ddd", builder.toString());

        builder.clear().append("qwe");

        char[] expectedChars = {'q', 'w', 'e'};
        assertEquals(expectedChars[0], builder.toCharArray()[0]);
        assertEquals(expectedChars[1], builder.toCharArray()[1]);
        assertEquals(expectedChars[2], builder.toCharArray()[2]);

        builder.clear();
        assertEquals("", builder.toString());

        builder.append((String) null);

        assertEquals("", builder.toString());

        builder.setNullText("<null>");
        builder.append((String) null);

        assertEquals("<null>", builder.toString());


    }
}
