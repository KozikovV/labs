package common.collectionutils;

import org.apache.commons.collections.*;
import org.apache.commons.collections.buffer.*;

/**
 * User: zinchenko
 * Date: 9/14/13
 */
public class BufferMain {
    public static void main(String[] args) {
        Buffer buffer = new PriorityBuffer();
        buffer.add("----");
        buffer.add("----++");
        buffer.add("======");

        for (int i = 0; i < 10; i++) {
            System.out.println(buffer.get());
        }
    }
}
