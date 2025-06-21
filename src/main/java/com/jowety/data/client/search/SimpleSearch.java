package com.jowety.data.client.search;

import java.util.ArrayList;
import java.util.List;

public class SimpleSearch {

	/**
	 * Filter Strings must be of format "<path> <matchMode> <value>"
	 * @see com.jowety.data.query.Filter.MatchMode 
	 * All MatchMode enum values can be used as matchModes in the string (case insensitive)
	 * along with some shortcut aliases such as 'eq' and '=' for 'equals'. 
	 */
	protected List<String> filters = new ArrayList<>();
	/**
	 * Order Strings must be of format "<path> [asc/desc]"
	 * The asc/desc is optional and defaults to asc
	 */
	protected List<String> orders = new ArrayList<>();
	protected Integer firstResult;
	protected Integer maxResults;
	
	public List<String> getFilters() {
		return filters;
	}
	public void setFilters(List<String> filters) {
		this.filters = filters;
	}
	public List<String> getOrders() {
		return orders;
	}
	public void setOrders(List<String> orders) {
		this.orders = orders;
	}
	public Integer getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}
	public Integer getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	@Override
	public String toString() {
		return "SimpleSearch [filters=" + filters + ", orders=" + orders + ", firstResult=" + firstResult
				+ ", maxResults=" + maxResults + "]";
	}
}
