package com.jowety.data.client.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jowety.data.client.search.Filter.MatchMode;

public class Search implements Serializable{

	private static final long serialVersionUID = 1L;

	protected List<Select> selects;
	protected Collection<Filter> filters;
	protected List<OrderBy> orders;
	protected List<Exp> groups;
	protected Integer firstResult;
	protected Integer maxResults;
	protected boolean distinct;

	public boolean getDistinct() {
		return distinct;
	}
	public Search setDistinct(boolean distinct) {
		this.distinct = distinct;
		return this;
	}
	/**
	 * Convenience method for setting distinct = true
	 * @return
	 */
	public Search distinct() {
		this.distinct = true;
		return this;
	}
	public Collection<Filter> getFilters() {
		return filters;
	}
	@JsonProperty
	public Search setFilters(Collection<Filter> filters) {
		this.filters = filters;
		return this;
	}
	public Search setFilters(Filter... Filter) {
		this.filters = Arrays.asList(Filter);
		return this;
	}
	public List<Select> getSelects(){
		return selects;
	}
	public void setSelects(List<Select> selects) {
		this.selects = selects;
	}
	public List<OrderBy> getOrders() {
		return orders;
	}
	public Search setOrders(List<OrderBy> orders) {
		this.orders = orders;
		return this;
	}
	@JsonProperty
	public Search setOrders(OrderBy... orders) {
		this.orders = Arrays.asList(orders);
		return this;
	}
	public List<Exp> getGroups() {
		return groups;
	}
	@JsonProperty
	public void setGroups(List<Exp> groups) {
		this.groups = groups;
	}
	public Integer getFirstResult() {
		return firstResult;
	}
	public Search setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
		return this;
	}
	/**
	 * Same as setFirstResult, but shorter
	 * @param firstResult
	 * @return
	 */
	public Search first(Integer firstResult) {
		this.firstResult = firstResult;
		return this;
	}
	public Integer getMaxResults() {
		return maxResults;
	}
	public Search setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
		return this;
	}
	/**
	 * Same as setMaxResults, but shorter
	 * @param maxResults
	 * @return
	 */
	public Search max(Integer maxResults) {
		this.maxResults = maxResults;
		return this;
	}
	public Search addFilter(Filter filter){
		if(this.filters == null)
			this.filters = new ArrayList<Filter>();
		this.filters.add(filter);
		return this;
	}
	/**
	 * Convenience method for adding a Filter
	 * @param path
	 * @param value
	 * @param match
	 * @return
	 */
	public Search Filter(String path, Object value, MatchMode match){
		this.addFilter(new Filter(path, value, match));
		return this;
	}
	public Search Filter(Function func, Object value, MatchMode match){
		this.addFilter(new Filter(func, value, match));
		return this;
	}
	public Search Filter(Exp exp, Object value, MatchMode match){
		this.addFilter(new Filter(exp, value, match));
		return this;
	}
	public Search filterPaths(String leftPath, String rightPath, MatchMode match){
		this.addFilter(new Filter(Exp.path(leftPath), Exp.path(rightPath), match));
		return this;
	}
	/**
	 * @param property
	 * @param fromDate GREATER_THAN_OR_EQUAL match. If null, no fromDate filter added.
	 * @param toDate LESS_THAN match. If null, no toDate filter added.
	 * @return
	 */
	public Search filterDateRange(String property, Date fromDate, Date toDate){
		if(fromDate != null) Filter(property, getTopOfDay(fromDate), MatchMode.GREATER_THAN_OR_EQUAL);
		if(toDate != null) Filter(property, getTopOfNextDay(toDate), MatchMode.LESS_THAN);
		return this;
	}
	/**
	 * Convenience method to create an OR grouping
	 * @param filters
	 * @return
	 */
	public Search createOrGroup(Filter... filters){
		Collection<Filter> orSet = new HashSet<Filter>();
		for(Filter filter: filters)
			orSet.add(filter);
		this.addFilter(new Filter("", orSet, MatchMode.OR));
		return this;
	}

	public OrGroup beginOrGroup(){
		OrGroup og = new OrGroup(this);
		this.addFilter(og);
		return og;
	}

	/**
	 * Convenience method for adding a Filter with MatchMode.EQUALS
	 * @param path the property path to filter by
	 * @param value the value to restrict the property
	 * @return
	 */
	public Search Filter(String path, Object value){
		this.addFilter(new Filter(path, value, MatchMode.EQUALS));
		return this;
	}
	public Search notNull(String path){
		this.addFilter(new Filter(path, null, MatchMode.NOT_NULL));
		return this;
	}
	public Search isNull(String path){
		this.addFilter(new Filter(path, null, MatchMode.NULL));
		return this;
	}
	public Search Filter(Function func, Object value){
		this.addFilter(new Filter(func, value, MatchMode.EQUALS));
		return this;
	}
	public Search Filter(Exp exp, Object value){
		this.addFilter(new Filter(exp, value, MatchMode.EQUALS));
		return this;
	}
	public Search Filter(Collection<Filter> value, MatchMode match){
		this.addFilter(new Filter(value, match));
		return this;
	}
	public Search addOrderBy(OrderBy orderby){
		if(this.orders == null)
			this.orders = new ArrayList<OrderBy>();
		this.orders.add(orderby);
		return this;
	}
	/**
	 * Convenience method for adding an OrderBy
	 * @param orderby
	 * @return
	 */
	public Search orderBy(String property, boolean ascending){
		if(this.orders == null)
			this.orders = new ArrayList<OrderBy>();
		this.orders.add(new OrderBy(property, ascending));
		return this;
	}
	public Search orderByAsc(String property){
		return orderBy(property, true);
	}
	public Search orderByDesc(String property){
		return orderBy(property, false);
	}

	public Search addGroupByPath(String path){
		if(this.groups == null)
			this.groups = new ArrayList<Exp>();
		this.groups.add(Exp.path(path));
		return this;
	}
	public Search addGroupByFunction(Function func){
		if(this.groups == null)
			this.groups = new ArrayList<>();
		this.groups.add(Exp.function(func));
		return this;
	}
	
	public Search select(String... select) {
		if(selects == null)
			selects = new ArrayList<>();
		for(String s: select)
			selects.add(new Select(s));
		return this;
	}

	public Search select(Select... select) {
		if(selects == null)
			selects = new ArrayList<>();
		for(Select s: select)
			selects.add(s);
		return this;
	}

	/**
	 * Starts a method chain which creates a Filter on this Search
	 * @return a LeftSide object
	 */
	public Filter.LeftSide where(){
		Filter f = new Filter();
		addFilter(f);
		return f.startWhere(this);
	}
	
	/**
	 * Set the passed Calendar to the top of the day
	 * @param cal
	 * @return
	 */
	public static Calendar setTopOfDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	/**
	 * Returns a new Date at the top of the day
	 * @param d
	 * @return
	 */
	public static Date getTopOfDay(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return setTopOfDay(cal).getTime();
	}
	/**
	 * Sets the passed in Calendar to the top of the next day
	 * @param cal
	 * @return
	 */
	public static Calendar setTopOfNextDay(Calendar cal) {
		cal = setTopOfDay(cal);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		return cal;
	}
	/**
	 * Returns a new Date at the top of tomorrow.
	 * @param d
	 * @return
	 */
	public static Date getTopOfNextDay(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return setTopOfNextDay(cal).getTime();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("distinct", this.distinct)
				.append("filters", this.filters)
				.append("firstResult", this.firstResult)
				.append("groups", this.groups)
				.append("maxResults", this.maxResults)
				.append("orders", this.orders)
				.append("selects", this.selects)
				.toString();
	}
	
	
}
