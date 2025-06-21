package com.jowety.data.client.search;

import java.util.ArrayList;
import java.util.Collection;

public class OrGroup extends Filter {

	private Collection<Filter> filters = new ArrayList<Filter>();
	private Search search;



	public OrGroup(Search s) {
		super();
		this.setRightSide(Exp.literal(filters));
		this.setMatchMode(MatchMode.OR);
		this.search = s;
	}
	public OrGroup addFilterBy(Filter filter){
		if(this.filters == null)
			this.filters = new ArrayList<Filter>();
		this.filters.add(filter);
		return this;
	}
	public OrGroup filterBy(String path, Object value, MatchMode match){
		this.addFilterBy(new Filter(path, value, match));
		return this;
	}
	public OrGroup filterBy(Function func, Object value, MatchMode match){
		this.addFilterBy(new Filter(func, value, match));
		return this;
	}
	public OrGroup filterBy(Exp exp, Object value, MatchMode match){
		this.addFilterBy(new Filter(exp, value, match));
		return this;
	}
	public OrGroup filterPaths(String leftPath, String rightPath, MatchMode match){
		this.addFilterBy(new Filter(Exp.path(leftPath), Exp.path(rightPath), match));
		return this;
	}
	/**
	 * Convenience method for adding a FilterBy with MatchMode.EQUALS
	 * @param path the property path to filter by
	 * @param value the value to restrict the property
	 * @return
	 */
	public OrGroup filterBy(String path, Object value){
		this.addFilterBy(new Filter(path, value, MatchMode.EQUALS));
		return this;
	}
	public OrGroup notNull(String path){
		this.addFilterBy(new Filter(path, null, MatchMode.NOT_NULL));
		return this;
	}
	public OrGroup isNull(String path){
		this.addFilterBy(new Filter(path, null, MatchMode.NULL));
		return this;
	}
	public OrGroup filterBy(Function func, Object value){
		this.addFilterBy(new Filter(func, value, MatchMode.EQUALS));
		return this;
	}
	public OrGroup filterBy(Exp exp, Object value){
		this.addFilterBy(new Filter(exp, value, MatchMode.EQUALS));
		return this;
	}
	public OrGroup filterBy(Collection<Filter> value, MatchMode match){
		this.addFilterBy(new Filter(value, match));
		return this;
	}

	public Search endOrGroup(){
		return search;
	}
}
