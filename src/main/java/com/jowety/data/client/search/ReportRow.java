package com.jowety.data.client.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportRow  implements Serializable, RowHolder {

	private static final long serialVersionUID = 3381529054605981861L;

	private List<ReportCell> cells = new ArrayList<ReportCell>();
	private transient Map<String, ReportCell> cellMap = new LinkedHashMap<>();
	
	//Only used in multilevel reports
	private List<ReportRow> rows = new ArrayList<ReportRow>();
		

	public List<ReportCell> getCells() {
		return cells;
	}
	public void setCells(List<ReportCell> cells) {
		this.cells = cells;
		for(ReportCell cell: cells) {
			cellMap.put(cell.getName(), cell);
		}
	}
	public List<ReportRow> getRows() {
		return rows;
	}
	public void setRows(List<ReportRow> detail) {
		this.rows = detail;
	}
	public RowHolder addRow(ReportRow rr) {
		rows.add(rr);
		return this;
	}
	
	public ReportRow addCell(ReportCell cell) {
		cells.add(cell);
		cellMap.put(cell.getName(), cell);
		return this;
	}
	
	public ReportCell getCellByName(String name) {
		return cellMap.get(name);
	}
	
	
	@Override
	public String toString() {
		return toString(", ");
	}
	public String toString(String separator) {
		StringBuilder sb = new StringBuilder();
		for(Iterator<ReportCell> i = cells.iterator(); i.hasNext();) {
			ReportCell cell = i.next();
			sb.append(cell.getName() + "=" + cell.getDisplay());
			if(i.hasNext()) sb.append(separator);
		}
		return sb.toString();
	}
	
}
