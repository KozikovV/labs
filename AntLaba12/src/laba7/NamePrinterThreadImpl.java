package laba7;

import java.io.PrintStream;

import interfaces.task7.simple.NamePrinterThread;

/**
 * Печатает имя в поток.
 * 
 * @author zinchenko
 *
 */
public class NamePrinterThreadImpl extends NamePrinterThread {

    private int count = 0;
    private long interval = 0;
    private String name;
    private PrintStream stream;

    @Override
    public void setCount(final  int arg0) {
        if (arg0 <= 0) {
            throw new IllegalArgumentException();
        }

        count = arg0;
    }

    @Override
    public void setInterval(final long arg0) {
        if (arg0 <= 0) {
            throw new IllegalArgumentException();
        }

        interval = arg0;
    }

    @Override
    public void setPrintName(final String arg0) {
        if (arg0.isEmpty()) {
            throw new IllegalArgumentException();
        }

        name = arg0;

    }

    @Override
    public void setStream(final PrintStream arg0) {
        if (arg0 == null) {
            throw new NullPointerException();
        }

        stream = arg0;

    }

    @Override
    public void run() {
        synchronized (stream) {
            for (int i = count; i != 0; i--) {
                stream.print(name);
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

}
