package myTools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DisplayObject {
	// 获取所有属性
	public static Object[] getFields(Object obj, Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getMethods();
		Object[] values = new Object[fields.length];
		try {
			for (int i = 0; i < fields.length; i++) {
				String setMethod = "get" + fields[i].getName();
				for (Method method : methods) {
					if (method.getName().toLowerCase()
							.equals(setMethod.toLowerCase())) {
						values[i] = method.invoke(obj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values;
	}
}
