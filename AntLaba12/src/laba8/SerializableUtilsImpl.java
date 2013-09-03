package laba8;

import interfaces.task8.SerializableUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class SerializableUtilsImpl implements SerializableUtils {

	@Override
	public Object deserialize(InputStream arg0) {

		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(arg0);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Object object = null;
		try {
			object = ois.readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return object;
	}

	@Override
	public void serialize(OutputStream arg0, Object arg1) {

		if (arg1 == null) {
			throw new NullPointerException();
		}

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(arg0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(arg1);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
