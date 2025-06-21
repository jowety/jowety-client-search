/**
 * 
 */
package com.jowety.data.client.search;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Jon.Tyree
 *
 */
public class OrderBy implements Serializable{

	private String property;
	private boolean ascending = true;	
	private boolean outerjoin = false;
	
	public OrderBy() {
	}
	
	/**
     * @param property The object property name to sort on
     * @param ascending The sort direction. Set false for DESC
	 * @param outerjoin True will set a LEFT OUTER JOIN for the FIRST node in the property path. Only applies to nested properties!
	 */
	public OrderBy(String property, boolean ascending, boolean outerjoin) {
        this.property = property;
        this.ascending = ascending;
        this.outerjoin = outerjoin;
    }

    /**
	 * @param property The object property name to sort on
	 * @param ascending The sort direction. Set false for DESC
	 */
	public OrderBy(String property, boolean ascending) {
		super();
		this.property = property;
		this.ascending = ascending;
	}
	/**
	 * Sort direction is ASC for this constructor
	 * @param property The object property name to sort on
	 */
	public OrderBy(String property) {
		super();
		this.property = property;
		this.ascending = true;
	}	

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * @param property
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	/**
	 * @return the ascending
	 */
	public boolean isAscending() {
		return ascending;
	}
	/**
	 * @param ascending
	 */
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
	
    public boolean isOuterjoin() {
        return outerjoin;
    }
   
    public void setOuterjoin(boolean outerjoin) {
        this.outerjoin = outerjoin;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

}
