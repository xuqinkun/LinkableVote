package com.xqk.util;

public class TypeParser {
	
	public static Object toType(String src, Class<?> type) {
		Object desc = null;
		try {
			switch (type.getSimpleName()) {
			case "Integer":
				desc = Integer.parseInt(src);
				break;
			case "Double":
				desc = Double.parseDouble(src);
				break;
			default:
				desc = src;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			desc = null;
		}
		return desc;
	}
}
