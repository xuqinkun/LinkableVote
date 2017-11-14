package com.xqk.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.xqk.pojo.ErrorMessage;
import com.xqk.pojo.ErrorMessageWrapper;

@Component
public class FormValidator {
	private InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("ValidationMessages.properties");
	private Properties prop = new Properties();
//	private Log log = LogFactory.getLog(FormValidator.class); 
	
	public FormValidator() {
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, String> getArguments(String src) {
		Map<String, String> args = new HashMap<String, String>();
		if (!(src.contains("(") && src.contains(")"))) {
			return null;
		}
		String values = src.substring(src.indexOf("(") + 1, src.lastIndexOf(")"));
		String[] pairs = values.split(",");
		for (String pair : pairs) {
			String[] group = pair.split("=");
			if (!group[1].equals("[]")) {
				args.put(group[0].trim(), group[1].trim());
			}
		}
		return args;
	}

	public ErrorMessageWrapper validate(Map<String, String> params, Class<?> clazz) {
		List<ErrorMessage> errorList =  new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			Annotation[] annotations = field.getAnnotations();
			String fieldValue = params.get(fieldName);
			
			for (Annotation annotation : annotations) {
				String errorMsg = "";
				Class<? extends Annotation> type = annotation.annotationType();
				String typeName = type.toString();
				String typeSimpleName = typeName.substring(typeName.lastIndexOf(".") + 1);
				ErrorMessage errorMessage = null;
				Map<String, String> args = getArguments(annotation.toString());
				String key = args.get("message");
				if (typeSimpleName.equals("NotNull") && fieldValue == null) {
					errorMsg = prop.getProperty(key);
					if (errorMsg == null) {
						errorMsg = fieldName + " con not be null";
					}
					errorMessage = new ErrorMessage(fieldName, errorMsg);
					errorList.add(errorMessage);
					break;
				}
				else if (typeSimpleName.equals("Size")) {
					String minStr = args.get("min");
					int min = minStr != null ? Integer.parseInt(minStr) : Integer.MIN_VALUE;
					String maxStr = args.get("max");
					int max = minStr != null ? Integer.parseInt(maxStr) : Integer.MAX_VALUE;
					int len = fieldValue.length();
					if (len < min || len > max) {
						errorMsg = prop.getProperty(key);
						if (errorMsg != null && errorMsg.contains("min") && errorMsg.contains("max")) {
							errorMsg = errorMsg.replace("{min}", String.valueOf(min));
							errorMsg = errorMsg.replace("{max}", String.valueOf(max));
						} else {
							errorMsg = fieldName + " must be " + min + " and " + max + " long!";
						}
						
					}
				}
				else if (typeSimpleName.equals("Pattern")) {
					String regex = args.get("regexp");
					if (!fieldValue.matches(regex)) {
						errorMsg = prop.getProperty(args.get("message"));
						if (errorMsg == null) {
							errorMsg = fieldName + " should be valid!";
						}
					}
				}
				errorMessage = new ErrorMessage(fieldName, errorMsg);
				errorList.add(errorMessage);
			}
		}
		return new ErrorMessageWrapper(errorList);
	}

}
