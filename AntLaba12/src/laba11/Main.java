package laba11;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import interfaces.logging.LoggingArrayCollection;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LoggingArrayCollection<Integer> logArray = new LogginArrayCollectionImpl<Integer>();
		for (int i = 0; i < 2000; i++) {
			try {
				logArray.add(1);
				logArray.add(2);
				logArray.addAll(logArray);
			} catch (IllegalArgumentException e) {
				System.out.println("catch IllegalArgumentException");
			}
		}

		System.out.println("end");
	}

}
