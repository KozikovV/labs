package laba7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import interfaces.task7.executor.CopyTask;
import interfaces.task7.executor.Executor;
import interfaces.task7.executor.TasksStorage;
import interfaces.task7.simple.NamePrinterRunnable;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

//        try {
//            CopyTask ct = new CopyTaskImpl();
//            ct.setSource(System.getProperty("java.io.tmpdir"));
//        } catch (Exception e) {
//            if (e.getCause()!=null) {
//                System.out.println("qweqweqwe");
//            }
//        }

        // / ---------------------

        BigInteger bd1 = new BigInteger(10, new Random());

        System.out.println(bd1);

        String pathname = "test\\d7.txt";

        PrintStream printStream = null;
        try {
            printStream = new PrintStream(new FileOutputStream(new File(
                    pathname)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // - RAN -----------------------------------------------------------

        NamePrinterRunnable npr = new NamePrinterRunnableImpl();
        npr.setCount(4);
        npr.setInterval(1000);
        npr.setPrintName("MyName...");
        npr.setStream(printStream);

        Thread thread1 = new Thread(npr);

        thread1.start();

        // --- THREAD ----------------------------------------------------

        NamePrinterThreadImpl thread2 = new NamePrinterThreadImpl();

        thread2.setCount(4);
        thread2.setInterval(1000);
        thread2.setPrintName("My2name!!!!");
        thread2.setStream(printStream);

        thread2.start();

        // EXECUTOR ------------------------------------------------------

        TasksStorage ts = new TasksStorageImpl();

        ts.add(new SumTaskImpl(13));
        ts.add(new CopyTaskImpl("test\\sfc1.txt", "test\\dfc1.txt"));
        ts.add(new SumTaskImpl(23));
        ts.add(new CopyTaskImpl("test\\sfc2.txt", "test\\dfc2.txt"));
        ts.add(new SumTaskImpl(34));
        ts.add(new CopyTaskImpl("test\\sfc3.txt", "test\\dfc3.txt"));
        ts.add(new SumTaskImpl(45));
        ts.add(new SumTaskImpl(56));

        Executor executor1 = new ExecutorImpl();
        executor1.setStorage(ts);
        executor1.start();

        Executor executor2 = new ExecutorImpl();
        executor2.setStorage(ts);
        executor2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        executor1.stop();
        executor2.stop();

        // printStream.close();
        System.out.println("end");
    }
}
