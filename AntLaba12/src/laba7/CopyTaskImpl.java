package laba7;

import interfaces.task7.executor.CopyTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Задача копирования.
 * 
 * @author zinchenko
 * 
 */
public class CopyTaskImpl implements CopyTask {

    private int tryCount = 0;
    private String dest;
    private String source;

    /**
     * Конструктор с параметрами.
     * 
     * @param source
     *            исходный файл
     * @param dest
     *            цель
     */
    public CopyTaskImpl(String source, String dest) {

        this.source = source;
        this.dest = dest;

    }

    /**
     * Конструктор без параметров.
     * 
     * 
     */
    public CopyTaskImpl() {

    }

    @Override
    public boolean execute() throws Exception {

        // IOUtils ioUtils = new IOUtilsImpl();
        //
        // try {
        // ioUtils.copyFileBest(source, dest);
        // } catch (Exception e) {
        // return false;
        // }

        FileInputStream input = null;

        try {
            input = new FileInputStream(source);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        FileOutputStream output = null;

        try {
            output = new FileOutputStream(dest);
        } catch (FileNotFoundException e) {
            File fileDest = new File(dest);
            try {
                fileDest.createNewFile();
            } catch (IOException e1) {
                throw new IllegalArgumentException(e1);
            } finally {
                try {
                    input.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

        FileChannel inputChannel = input.getChannel();
        FileChannel ouptupChannel = output.getChannel();

        try {
            inputChannel.transferTo(0, inputChannel.size(), ouptupChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public int getTryCount() {
        return tryCount;
    }

    @Override
    public void incTryCount() {
        tryCount++;

    }

    @Override
    public void setDest(String arg0) {

        if (arg0 == null) {
            throw new NullPointerException();
        }

        dest = arg0;

    }

    @Override
    public void setSource(String arg0) {

        if (arg0 == null) {
            throw new NullPointerException();
        }
        
        if (!arg0.matches(".*[.]{1}.*")) {
            throw new IllegalArgumentException(new IllegalArgumentException());
        }

        source = arg0;

    }

}
