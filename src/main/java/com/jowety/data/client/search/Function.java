package com.jowety.data.client.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Function implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private Class type;
	private List<Exp> args = new ArrayList<Exp>();
	
	
	public Function() {
	}

	public Function(String name, Class type) {
		this.name = name;
		this.type = type;
	}
	public Function pathArg(String path) {
		args.add(Exp.path(path));
		return this;
	}
	public Function functionArg(Function func) {
		args.add(Exp.function(func));
		return this;
	}
	public Function literalArg(String value) {
		args.add(Exp.literal(value));
		return this;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public Class getType() {
		return type;
	}

	/**
	 * @return the args
	 */
	public List<Exp> getArgs() {
		return args;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Class type) {
		this.type = type;
	}

	/**
	 * @param args the args to set
	 */
	public void setArgs(List<Exp> args) {
		this.args = args;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
	

}
