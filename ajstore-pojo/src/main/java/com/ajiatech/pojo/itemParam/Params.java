package com.ajiatech.pojo.itemParam;

import java.util.List;

public class Params {
	private String key;

	private String form;

	private List<String> values;
	
	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getForm() {
		return this.form;
	}

	

}
