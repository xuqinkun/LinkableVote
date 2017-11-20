package com.xqk.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.xqk.pojo.ErrorMessage;
import com.xqk.pojo.ErrorMessageWrapper;

@Component
public class FormValidator {
	private static InputStream in = Thread.currentThread().
			getContextClassLoader().getResourceAsStream("properties/ValidationMessages.properties");
	private static Properties prop = new Properties();
	private Log log = LogFactory.getLog(FormValidatorTest.class); 

	static {
		try {
			InputStreamReader reader = new InputStreamReader(in, "UTF-8");
			prop.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, String> getArguments(String src) {
		log.info("=========== getArguments:" + src +" ===================");
		Map<String, String> args = new HashMap<String, String>();
		if (!(src.contains("(") && src.contains(")"))) {
			return null;
		}
		String values = src.substring(src.indexOf("(") + 1, src.lastIndexOf(")"));
		String[] pairs = values.split(",");
		for (String pair : pairs) {
			String[] group = pair.split("=");
			if (group != null && group.length == 2) {
				if (!group[1].equals("[]")) {
					args.put(group[0].trim(), group[1].trim());
				}
			}
		}
		return args;
	}

	public ErrorMessageWrapper validate(Map<String, String> params, Class<?> clazz) {
		log.info("============ Validate " + clazz.getName() + " ===========");
		List<ErrorMessage> errorList =  new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String fieldValue = params.get(fieldName);
			
			NotNull notNull = field.getAnnotation(NotNull.class);
			Size size = field.getAnnotation(Size.class);
			Pattern pattern = field.getAnnotation(Pattern.class);
			
			ErrorMessage errorMessage = null;
			if (notNull != null && fieldValue == null) {
				String message = prop.getProperty(notNull.message());
				errorMessage = new ErrorMessage(fieldName, message);
				errorList.add(errorMessage);
				continue;
			}
			if (size != null) {
				int min = size.min();
				int max = size.max();
				int len = fieldValue.length();
				if (len < min || len > max) {
					String message = prop.getProperty(size.message());
					if (message == null) {
						message = fieldName + " should be " + min + " and " + max + " long.";
					} else {
						message = message.replace("{min}", String.valueOf(min)).
								replace("{max}", String.valueOf(max));
					}
					errorMessage = new ErrorMessage(fieldName, message);
					errorList.add(errorMessage);
				}
			}
			if (pattern != null) {
				if (!fieldValue.matches(pattern.regexp())) {
					String message = prop.getProperty(pattern.message());
					if (message == null) {
						message = fieldName + " is not valid.";
					}
					errorMessage = new ErrorMessage(fieldName, message);
					errorList.add(errorMessage);
				}
			}
			
		}
		return new ErrorMessageWrapper(errorList);
	}

}
