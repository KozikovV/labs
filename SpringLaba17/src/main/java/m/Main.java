package m;

import java.text.ParseException;
import java.util.Date;

import com.nixsolutions.web.formatter.DateFormatter;

public class Main {

    /**
     * @param args
     * @throws ParseException 
     */
    public static void main(String[] args) throws ParseException {
        String s = new DateFormatter().print(new Date(), null);
        Date d = new DateFormatter().parse("2013-11-12", null);
        
        System.out.println("sdf");
    }

}
