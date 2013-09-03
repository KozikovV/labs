package laba4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import interfaces.task4.CollectionUtils;

public class CollectionUtilImpl implements CollectionUtils {

    /**
     * Разница двух соллекций.
     * 
     * @param c1
     *            первая коллекция
     * @param c2
     *            первая коллекция
     * @return результирующая коллекция
     * 
     */
    @Override
    public Collection<Integer> difference(final Collection<Integer> c1,
            final Collection<Integer> c2) {
        if (c1==null||c2==null) {
            throw new NullPointerException();
        }
        Collection<Integer> integers = new ArrayList<Integer>(c1);
        integers.removeAll(c2);
        return integers;
    }

    /**
     * Пересечение двух коллекций.
     * 
     * @param c1
     *            первая коллекция
     * @param c2
     *            первая коллекция
     * @return результирующая коллекция
     */
    @Override
    public List<Integer> intersection(final Collection<Integer> c1,
            final Collection<Integer> c2) {
        
        if (c1==null||c2==null) {
            throw new NullPointerException();
        }
        
        List<Integer> integers1 = new ArrayList<Integer>(c1);
        integers1.retainAll(c2);
        List<Integer> integers2 = new ArrayList<Integer>(c2);
        integers2.retainAll(integers1);
        integers1.addAll(integers2);
        return integers1;
    }

    /**
     * Пересечение двух коллекций без дубликатов.
     * 
     * @param c1
     *            первая коллекция
     * @param c2
     *            первая коллекция
     * @return результирующая коллекция
     */
    @Override
    public Set<Integer> intersectionWithoutDuplicate(
            final Collection<Integer> c1, final Collection<Integer> c2) {
        if (c1==null||c2==null) {
            throw new NullPointerException();
        }
        
        Set<Integer> integers = new HashSet<Integer>(c1);
        integers.retainAll(c2);
        return integers;
    }

    /**
     * Сумма двух коллекций.
     * 
     * @param c1
     *            первая коллекция
     * @param c2
     *            первая коллекция
     * @return результирующая коллекция
     */
    @Override
    public List<Integer> union(final Collection<Integer> c1,
            final Collection<Integer> c2) {

        List<Integer> list = new ArrayList<Integer>(c1.size() + c2.size());
        list.addAll(c1);
        list.addAll(c2);

        return list;
    }

}
