package com.jowety.data.client.search;

import java.util.List;


public class SearchResult<T> {
	
	List<T> results;
	Class<T> resultType;
	Integer firstResult;
	Integer pageSize;
	Integer totalCount;
	
	public SearchResult() {
	}
	
	public SearchResult(List<T> results, Integer totalCount) {
		this.results = results;
		this.totalCount = totalCount;
	}
	
	public SearchResult(List<T> results, Integer totalCount, Class<T> type) {
		this.results = results;
		this.totalCount = totalCount;
		this.resultType = type;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Class<T> getResultType() {
		return resultType;
	}

	public void setResultType(Class<T> resultType) {
		this.resultType = resultType;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer maxResults) {
		this.pageSize = maxResults;
	}

	@Override
	public String toString() {
		return "SearchResult [resultType=" + resultType + ", firstResult=" + firstResult
				+ ", maxResults=" + pageSize + ", totalCount=" + totalCount + "]";
	}


	
	
	

}
