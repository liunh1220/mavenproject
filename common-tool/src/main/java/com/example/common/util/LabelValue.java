package com.example.common.util;

public class LabelValue {

	private String label;
	private String value;

	public LabelValue(String label, String value) {
		this.label = label;
		this.value = value;
	}
	
	public LabelValue() {
		
	}

	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
