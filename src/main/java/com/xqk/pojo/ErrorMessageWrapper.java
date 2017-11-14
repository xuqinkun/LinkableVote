package com.xqk.pojo;

import java.util.List;

public class ErrorMessageWrapper {
	private List<ErrorMessage> errors;
	
	public ErrorMessageWrapper(List<ErrorMessage> errors) {
		this.errors = errors;
	}

	public List<ErrorMessage> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorMessage> errors) {
		this.errors = errors;
	}

	public boolean hasErrors() {
		return errors != null && errors.size() > 0;
	}
}
