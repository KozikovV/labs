package laba10;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import interfaces.task5.ArrayCollection;
import interfaces.task5.ArrayIterator;
import junit.framework.TestCase;
import laba5.ArrayCollectionImpl;


public class ArrayIteratorImplTest {

    private ArrayCollection<Integer> collection = new ArrayCollectionImpl<Integer>();

    private Integer[] integers = { 1, 2, 3 };

    @Test
    public void testGetArray() {
        collection.setArray(integers);
        ArrayIterator iterator = (ArrayIterator) collection.iterator();
        Assert.assertArrayEquals(integers, iterator.getArray());
    }

    @Test
    public void testHasNext() {
        assertFalse(collection.iterator().hasNext());
        collection.add(integers[0]);
        assertTrue(collection.iterator().hasNext());
    }

    @Test
    public void testNextWhenCollectinEmpty() {
        assertEquals(0, collection.size());
        Iterator iterator = collection.iterator();
        try {
            iterator.next();
            fail("should be threw NoSuchElementException when call next in empty collection");
        } catch (NoSuchElementException e) {
        } catch (Exception e) {
            fail("should throw NoSuchElementException");
        }
    }

    @Test
    public void testNext() {
        collection.addAll(Arrays.asList(integers));
        Iterator<Integer> iter = collection.iterator();
        assertEquals(integers[0], iter.next());
        assertEquals(integers[1], iter.next());
    }

    @Test
    public void testRemoveWithoutNext() {
        collection.addAll(Arrays.asList(integers));
        Iterator iter = collection.iterator();
        try {
            iter.remove();
            fail("should be threw IllegalStateException whre call remove witout next");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail("should be threw IllegalStateException whre call remove witout next");
        }

    }

    @Test
    public void testRemoveAfterRemove() {
        collection.addAll(Arrays.asList(integers));
        Iterator iter = collection.iterator();
        iter.next();
        iter.next();
        iter.next();
        assertFalse(iter.hasNext());
        try {
            iter.remove();
            fail("should be threw IllegalStateException whre call remove witout next");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail("should be threw IllegalStateException whre call remove witout next");
        }
    }

    @Test
    public void testRemoveBeforeNext() {
        try {
            collection.iterator().remove();
            fail("should be throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail("should be throw IllegalStateException");
        }
    }

    @Test
    public void testRemove() {
        collection.addAll(Arrays.asList(integers));
        Iterator<Integer> iter = collection.iterator();
        iter.next();
        iter.remove();
        assertEquals(integers.length - 1, collection.size());
    }
}


