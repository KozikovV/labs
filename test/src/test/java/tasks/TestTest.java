package tasks;

import org.junit.Test;
import org.springframework.util.StopWatch;

public class TestTest {
    @Test
    public void test() {
        String s = "qweqweqwe";
        long n = 999999999;

        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start();
        f1(s, n);
        stopWatch1.stop();
        System.out.println(stopWatch1.getTotalTimeMillis());

        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        f2(s, n);
        stopWatch2.stop();
        System.out.println(stopWatch2.getTotalTimeMillis());

    }

    private void f1(String s, long n) {
        String newS = null;
        for (long i = 0; i < n; i++) {
            if (s != null) {
                newS = s;
            }
        }
    }

    private void f2(String s, long n) {
        String newS = null;
        for (long i = 0; i < n; i++) {
            try {
                newS = s;
            } catch (NullPointerException e) {

            }
        }
    }
}
