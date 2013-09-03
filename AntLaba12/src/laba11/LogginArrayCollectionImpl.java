package laba11;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import interfaces.logging.LoggingArrayCollection;
import interfaces.task5.ArrayCollection;
import interfaces.task5.ArrayIterator;

public class LogginArrayCollectionImpl<E> implements LoggingArrayCollection<E> {
	
	private static final Log LOG = LogFactory.getLog(LogginArrayCollectionImpl.class);

	private static final int DEF_SIZE = 3;

	private int langth;

	private int size = 0;

	private E[] array = (E[]) new Object[0];

	/**
	 * Iterator.
	 * 
	 * @author zinchenko
	 * 
	 */
	private class ArrayIteratorImpl implements ArrayIterator<E> {
		private int cursor;
		private int current;
		private boolean nextWasCall;
		
		public ArrayIteratorImpl() {
			LOG.trace("created ArrayIterator");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			
			LOG.trace("hasNext invoked");
			
			if (cursor == size) {
				LOG.error("not any items or here is and of the array");
				return false;
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public E next() {
			
			LOG.trace("next invoked");
			
			if (cursor >= size) {
				LOG.error("here is and of the array");
				throw new NoSuchElementException();
			} else {
				current = cursor++;
				nextWasCall = true;
				return array[current];
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public void remove() {
			
			LOG.trace("remove invoked");
			
			if (!nextWasCall) {
				LOG.error("should invoke next before remome");
				throw new IllegalStateException();
			}
			if (!hasNext()) {
				LOG.error("not more items, it is end of array");
				throw new IllegalStateException();
			}
			removeByIndex(current);
			cursor--;
			nextWasCall = false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public Object[] getArray() {
			
			LOG.trace("getArray invoked");
			
			return array;
		}

	}
	
	public LogginArrayCollectionImpl() {
		LOG.trace("created LogginArrayCollectionImpl");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean add(final E e) {
		
		LOG.trace("add invoked with "+e);

		if (size == array.length) {
			increaseArray(1);
			array[size] = e;
			size++;
		} else {
			array[size] = e;
			size++;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean addAll(final Collection<? extends E> c) {
		
		LOG.trace("addAll invoked with "+c);

		if (this == c) {
			LOG.error("should add other Collection object");
			throw new IllegalArgumentException();
		}

		for (E e : c) {
			add(e);
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public void clear() {
		
		LOG.trace("cleare invoked");
		
		array = (E[]) new Object[0];
		size = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean contains(Object o) {
		
		LOG.trace("contains invoked with "+o);
		
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (array[i] == null) {
					return true;
				}
			}
		}

		for (int i = 0; i < size; i++) {
			if (array[i].equals(o)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		
		LOG.trace("containsAll invoked with "+c);
		
		for (Object object : c) {
			if (!contains(object)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean isEmpty() {
		
		LOG.trace("isEmpty invoked");

		if (size == 0) {
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public Iterator<E> iterator() {
		
		LOG.trace("iterator invoked");
		
		return new ArrayIteratorImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean remove(Object o) {
		
		LOG.trace("remove invoked with "+o);
		
		int fromNToEnd = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(o)) {
				removeByIndex(i);
				lengthDown(1);
				return true;
			}
		}
		return false;
	}

	/**
	 * Удаляет элемент из масива по индесу
	 * 
	 * @param n
	 *            индекс
	 */
	private void removeByIndex(int n) {
		
		LOG.trace("private removeByIndex invoked with "+n);
		
		int fromNToEnd = array.length - n - 1;
		System.arraycopy(array, n + 1, array, n, fromNToEnd);
		array[size - 1] = null;
		size--;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		
		LOG.trace("removeAll invoked with "+c);
		
		boolean modified = false;

		for (Object object : c) {
			if (remove(object)) {
				modified = true;

			}
		}

		return modified;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		
		LOG.trace("retainAll invoked with "+c);

		if (c == null) {
			LOG.error("argument must be not null");
			throw new NullPointerException();
		}

		boolean mofified = false;
		Object object = null;
		for (int i = 0; i < size; i++) {
			object = array[i];
			if (!c.contains(object)) {
				this.remove(object);
				mofified = true;
			}
		}

		return mofified;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public int size() {
		
		LOG.trace("size invoked");
		
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public Object[] toArray() {
		
		LOG.trace("toArray invoked");
		
		return array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		
		LOG.trace("toArray invoked with "+a);
		
		return (T[]) array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public Object[] getArray() {
		
		LOG.trace("getArray invoked");
		
		return array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public void setArray(E[] arg0) {
		
		LOG.trace("setArray invoked with "+arg0);
		
		array = arg0;
		langth = array.length;
		size = array.length;

	}

	/**
	 * Увеличение массива
	 */
	private void increaseArray(int forItem) {
		
		LOG.trace("increaseArray invoked with "+forItem);
		
		E[] newArray = (E[]) new Object[array.length + forItem];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}

	private void lengthDown(int forItem) {
		
		LOG.trace("lengtDown invoked with "+forItem);
		
		E[] newArray = (E[]) new Object[array.length - forItem];
		System.arraycopy(array, 0, newArray, 0, newArray.length);
		array = newArray;
	}

	@Override
	public Log getLogger() {
		
		LOG.trace("getLogger invoked");
		
		return LOG;
	}

}
