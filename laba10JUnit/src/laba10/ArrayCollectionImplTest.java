package laba10;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import junit.framework.TestCase;

import interfaces.task5.ArrayCollection;
import interfaces.task5.ArrayIterator;
import laba5.ArrayCollectionImpl;

public class ArrayCollectionImplTest extends TestCase {

	private ArrayCollection<Integer> collection = 
	        new ArrayCollectionImpl<Integer>();

	private Integer[] integers = { 1, 2, 3 };

	public void testIterator() {

		assertNotNull("should be not null", collection.iterator());
		assertTrue("Should be instance of ArrayIterator ",
				collection.iterator() instanceof ArrayIterator);

	}

	public void testAdd() {

		assertEquals(0, collection.size());
		assertTrue(collection.add(integers[0]));
		assertEquals(1, collection.size());

	}

	public void testAddAll() {
		assertEquals(0, collection.size());
		collection.addAll(Arrays.asList(integers));
		assertEquals(integers.length, collection.size());
	}
	
	public void testAddAllItself(){
	    collection.add(1);
	    assertEquals(1, collection.size());
	    try {
            collection.addAll(collection);
            fail("should be throw IllegalArgumentException when add itself");
        } catch (IllegalArgumentException e) {
            // TODO: handle exception
        }
	}

	public void testClear() {
		collection.addAll(Arrays.asList(integers));
		assertEquals(integers.length, collection.size());
		collection.clear();
		assertEquals(0, collection.size());

	}

	public void testContains() {
		collection.add(integers[0]);
		assertFalse(collection.contains(integers[1]));
		collection.add(integers[1]);
		assertTrue(collection.contains(integers[1]));

	}

	public void testContainsAll() {
		collection.add(-integers[0]);
		collection.add(integers[0]);
		assertFalse(collection.contains(Arrays.asList(integers)));
		collection.add(integers[1]);
		collection.add(integers[2]);
		assertFalse(collection.contains(Arrays.asList(integers)));
	}

	public void testGetArray() {
		collection.addAll(Arrays.asList(integers));
		assertArrayEquals(integers, collection.getArray());

	}

	public void testIsEmpty() {
		assertTrue(collection.isEmpty());
		collection.add(integers[0]);
		assertFalse(collection.isEmpty());
	}

	public void testRemove() {
		collection.addAll(Arrays.asList(integers));
		collection.remove(integers[0]);
		assertEquals(integers.length - 1, collection.size());
	}

	public void testRemoveAll() {
		collection.addAll(Arrays.asList(integers));
		collection.add(-integers[0]);
		assertEquals(integers.length + 1, collection.size());

	}

	public void testRetainAll() {
		collection.add(-integers[0]);
		collection.addAll(Arrays.asList(integers));
		assertTrue(collection.retainAll(Arrays.asList(integers)));
		assertEquals(integers.length, collection.size());
	}
	
	public void testRetainAllNull() {
	    collection.addAll(Arrays.asList(integers));
	    try {
	        collection.retainAll(null);
	        fail("should throw NullPointerException when pass nul into this method");
        } catch (NullPointerException e) {
            // TODO: handle exception
        }
	    
	}

	public void test() {
		collection.setArray(integers);
		assertArrayEquals(integers, collection.getArray());
	}

}
