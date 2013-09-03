package laba8;

import java.io.Serializable;

import interfaces.task8.CyclicCollection;
import interfaces.task8.CyclicItem;

/**
 * Циклическая коллекция.
 * 
 * @author zinchenko
 * 
 */
public class CyclicCollectionImpl implements CyclicCollection, Serializable {

	private int size;

	private CyclicItem first;
	private CyclicItem last;

	/**
	 * Конструктор по умолчанию.
	 */
	public CyclicCollectionImpl() {

	}

	/**
	 * Конструктор с параметром
	 * 
	 * @param first
	 *            первый элемент
	 */
	public CyclicCollectionImpl(final CyclicItem first) {
		super();
		add(first);
	}

	@Override
	public boolean add(final CyclicItem newItem) {
		if (first == null) {
			first = newItem;
			first.setNextItem(first);
			last = first;
			// last.setNextItem(first);
			size++;
		} else {
			boolean cont = isContain(newItem);
			if (cont) {
				throw new IllegalArgumentException();
			} else {
				last.setNextItem(newItem);
				last = newItem;
				last.setNextItem(first);
				size++;
			}
		}

		return true;
	}

	/**
	 * Проверка на содержимость элемента.
	 * 
	 * @param item
	 *            элемент
	 * @return {@code true} если содержит
	 */
	private boolean isContain(final CyclicItem item) {
		int n = size;
		CyclicItem currentItem = first;
		while (n != 0) {
			boolean cont = currentItem.equals(item);
			if (cont) {
				return true;
			}
			currentItem = currentItem.nextItem();
			n--;
		}
		return false;
	}

	@Override
	public CyclicItem getFirst() {
		return first;
	}

	@Override
	public void insertAfter(final CyclicItem item, final CyclicItem newItem) {

		if (item == null || newItem == null) {
			throw new NullPointerException();
		}

		if (!isContain(item)) {
			throw new IllegalArgumentException();
		}

		if (isContain(newItem)) {
			throw new IllegalArgumentException();
		}

		CyclicItem befItem = last;
		CyclicItem currItem = first;
		CyclicItem nextItem = first.nextItem();
		for (int i = 0; i < size; i++) {
			if (currItem.equals(item)) {
				if (size > 2) {
					currItem.setNextItem(newItem);
					newItem.setNextItem(nextItem);
					size++;
					return;
				} else if (size == 2) {
					currItem.setNextItem(newItem);
					newItem.setNextItem(nextItem);
					first = nextItem;
					size++;
					return;
				}
			}
			last = currItem;
			currItem = nextItem;
			nextItem = nextItem.nextItem();
		}

	}

	@Override
	public boolean remove(final CyclicItem item) {
		if (item == null) {
			throw new NullPointerException();
		}

		if (size == 0) {
			return false;
		}

		CyclicItem befItem = last;
		CyclicItem currItem = first;
		CyclicItem nextItem = first.nextItem();
		for (int i = 0; i < size; i++) {
			if (currItem.equals(item)) {
				if (size > 2) {
					currItem.setNextItem(null);
					befItem.setNextItem(nextItem);
					size--;
					return true;
				} else if (size == 2) {
					currItem.setNextItem(null);
					befItem.setNextItem(nextItem);
					first = nextItem;
					size--;
					return true;
				} else {
					first = null;
					size--;
					return true;
				}
			}
			last = currItem;
			currItem = nextItem;
			nextItem = nextItem.nextItem();
		}

		return false;
	}

	@Override
	public int size() {
		return size;
	}

}
