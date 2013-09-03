package laba5;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import interfaces.task2.FractionNumber;
import interfaces.task5.ArrayCollection;
import interfaces.task5.ArrayIterator;

/**
 * Коллекция.
 * 
 * @author zinchenko
 * 
 * @param <E>
 */
public class ArrayCollectionImpl<E> implements ArrayCollection<E> {

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

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if (cursor == size) {
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
			if (cursor >= size) {
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
			if (!nextWasCall) {
				throw new IllegalStateException();
			}
			if (!hasNext()) {
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
			return array;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean add(final E e) {

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

		if (this == c) {
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
		return new ArrayIteratorImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean remove(Object o) {
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

		if (c == null) {
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

		//
		// Iterator<E> iter = this.iterator();
		//
		// while (iter.hasNext()) {
		// // for (Object object : this) {
		// Object object = iter.next();
		// if (!c.contains(object)) {
		// iter.remove();
		// mofified = true;
		// }
		// }

		return mofified;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public Object[] toArray() {
		return array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return (T[]) array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public Object[] getArray() {
		return array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public void setArray(E[] arg0) {
		array = arg0;
		langth = array.length;
		size = array.length;

	}

	/**
	 * Увеличение массива
	 */
	private void increaseArray(int forItem) {
		E[] newArray = (E[]) new Object[array.length + forItem];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}

	private void lengthDown(int forItem) {
		E[] newArray = (E[]) new Object[array.length - forItem];
		System.arraycopy(array, 0, newArray, 0, newArray.length);
		array = newArray;
	}

}
