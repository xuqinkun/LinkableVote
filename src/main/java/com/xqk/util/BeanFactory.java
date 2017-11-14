package com.xqk.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class BeanFactory {
	
	public static <T> T getBean(Map<String, String> params, Class<T> clazz) {
		try {
			T obj = clazz.newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				String fieldName = field.getName();
				Class<?> fieldType = field.getType();
				String fieldValue = params.get(fieldName);
				String methodName = "set";
				methodName += fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method method = clazz.getMethod(methodName, fieldType);
				Object arg = TypeParser.toType(fieldValue, fieldType);
				method.invoke(obj, arg);
			}

			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return null;
	}
}
