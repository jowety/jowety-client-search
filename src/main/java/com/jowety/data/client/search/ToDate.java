package com.jowety.data.client.search;

import java.util.Date;


/**
 * @author Jon.Tyree
 * TO_DATE is an Oracle function. 
 */
public class ToDate extends Function {

	public ToDate() {
	}

	private static final long serialVersionUID = 1L;

	public ToDate(String path, String dateFormat) {
		super("TO_DATE", Date.class);
		pathArg(path).literalArg(dateFormat);
	}
	
	public ToDate(String path) {
		super("TO_DATE", Date.class);
		pathArg(path);
	}
}
