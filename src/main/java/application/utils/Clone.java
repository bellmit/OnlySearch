package application.utils;

import java.io.*;

/**
 * 
 * @author 王天龙 深度克隆
 * 
 */
public class Clone {

	public static Object deepClone(Object target) throws IOException,
			ClassNotFoundException {

		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(target);

		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (oi.readObject());
	}
}
