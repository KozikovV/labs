package laba5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import laba5.ArrayCollectionImpl;
import laba2.FractionNumberImpl;
import laba2.FractionNumberOperationImpl;
import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;
import interfaces.task5.ArrayCollection;
import interfaces.task5.ArrayIterator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<String>();

		Collection<FractionNumber> numbers = new ArrayCollectionImpl<FractionNumber>();
		numbers.add(new FractionNumberImpl(1, 2));
		numbers.add(new FractionNumberImpl(2, 1));
		numbers.add(new FractionNumberImpl(1, 3));

		FractionNumberOperation fno = new FractionNumberOperationImpl();

		FractionNumber fnRes = new FractionNumberImpl(0, 1);

		for (FractionNumber fractionNumber : numbers) {
			fnRes = fno.add(fnRes, fractionNumber);
		}

		System.out.println(fnRes.toStringValue());

		ArrayCollection<Double> arrayCollection = new ArrayCollectionImpl<Double>();
		ArrayIterator<Double> it = (ArrayIterator<Double>) arrayCollection
				.iterator();

		Double[] doubleArray = { 1d, 2d, 3d };

		arrayCollection.add(-doubleArray[0]);
		arrayCollection.add(doubleArray[0]);
		arrayCollection.add(doubleArray[2]);

		boolean ddd = arrayCollection.retainAll(Arrays.asList(doubleArray));

		System.out.println("end");

	}
}
