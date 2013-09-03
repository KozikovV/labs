package laba8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import interfaces.task8.CyclicCollection;
import interfaces.task8.CyclicItem;
import interfaces.task8.PathClassLoader;
import interfaces.task8.SerializableUtils;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CyclicItem f = new CyclicItemImpl(1, 2);
		CyclicItem s = new CyclicItemImpl(3, 4);

		CyclicCollection cc = new CyclicCollectionImpl();
		cc.add(f);
		cc.add(s);

		cc.remove(f);

		boolean b = s.equals(cc.getFirst());

		// SERIALIZABLE ------------------------------

		// создание выходного потока
		String fileName = "test\\ss8.b";

		OutputStream os = null;
		try {
			os = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// сериализация

		CyclicCollection serializable = new CyclicCollectionImpl();

		serializable.add(new CyclicItemImpl(1, 2));
		serializable.add(new CyclicItemImpl(2, 2));
		serializable.add(new CyclicItemImpl(3, 2));
		serializable.add(new CyclicItemImpl(4, 2));
		serializable.add(new CyclicItemImpl(4, 2));

		int ns = serializable.size();
		CyclicItem ci = serializable.getFirst();
		for (int i = ns; i != 0; i--) {
			System.out.println(ci);
			ci = ci.nextItem();
		}

		SerializableUtils su = new SerializableUtilsImpl();
		su.serialize(os, serializable);

		// создание входного потока

		InputStream is = null;
		try {
			is = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// десериализация

		CyclicCollectionImpl deserializabled = (CyclicCollectionImpl) su
				.deserialize(is);

		int nsd = deserializabled.size();
		CyclicItem cid = deserializabled.getFirst();
		for (int i = nsd; i != 0; i--) {
			System.out.println(cid);
			cid = cid.nextItem();
		}

		System.out.println("deserializabled: " + deserializabled);
		System.out.println("serializable.equals(deserializabled) = "
				+ serializable.equals(deserializabled));
		System.out.println(serializable == deserializabled);

		// CLASS LOADER ----------------------------------------------

		PathClassLoader pcl = new PathClassLoaderImpl();
		pcl.setPath("myFolder");

		ClassLoader cl = (ClassLoader) pcl;
		Class<?> c = null;
		try {
			c = cl.loadClass("laba1.Point");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object object = null;
		try {
			object = c.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("end");
	}

}
