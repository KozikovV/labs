package laba8;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import laba1.Point;

public class Main2 extends ClassLoader {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Main2 m = new Main2();
        m.f();

    }

    public void f() {
        String path = "myFolder";

        File fileOfClass = new File(path + File.separator + "Point.class");

        boolean exist = fileOfClass.exists();

        long length = fileOfClass.length();

        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(fileOfClass));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] bytes = new byte[(int) length];

        try {
            for (int i = 0; i < length; i++) {

                bytes[i] = (byte) is.read();

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class<?> result = defineClass("laba1.Point", bytes, 0, bytes.length);

        Object p = null;

        try {
            p = result.newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(p.toString());
        
        System.out.println("e");
    }

}
