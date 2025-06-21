package com.jowety.data.client.search;

import java.io.Serializable;

public class ReportCell implements Serializable {

	private static final long serialVersionUID = 2785914827197804945L;

	private String name;
	private Object value;
	private String display;
		
	public ReportCell(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}
	public ReportCell() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getDisplay() {
		return display!=null? display: (value!=null? value.toString(): "");
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	@Override
	public String toString() {
		return name + ": " + getDisplay();
	}
	
}
