package com.jowety.data.client.search;


/**
 * @author Jon.Tyree
 * LENGTH is an Oracle function. 
 * Path can be any character type
 * Return value is a number.
 */
public class Length extends Function {

	public Length() {
	}

	private static final long serialVersionUID = 1L;

	public Length(String path) {
		super("LENGTH", Integer.class);
		pathArg(path);
	}
}
