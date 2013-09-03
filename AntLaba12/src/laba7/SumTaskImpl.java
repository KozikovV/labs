package laba7;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import interfaces.task7.executor.SumTask;

/**
 * Задача суммирования.
 * 
 * @author zinchenko
 * 
 */
public class SumTaskImpl implements SumTask {

    private int tryCount;
    private long max;
    private int count;
    private BigInteger res = new BigInteger("0");

    /**
     * Конструктор без параметров.
     */
    public SumTaskImpl() {
    }

    /**
     * Конструктор с параметрами.
     */
    public SumTaskImpl(final int count) {
        this.count = count;
    }

    @Override
    public boolean execute() throws Exception {

        try {
            for (int i = count; i != 0; i--) {
                BigInteger bi = getRandom();
                res = res.add(bi);
            }
        } catch (Exception e) {
            return false;
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
    public BigInteger getRandom() {

        Random random = new Random();
        Integer val = random.nextInt((int) 12);
        BigInteger r = new BigInteger(val.toString());

        return r;
    }

    @Override
    public BigInteger getResult() {
        return res;
    }

    @Override
    public void setCount(final int arg0) {
        
        count = arg0;

    }

    @Override
    public void setMax(final long arg0) {
        if (arg0 <= 0) {
            throw new IllegalArgumentException();
        }
        max = arg0;
    }

}
