package com.jowety.data.client.search;

import java.io.Serializable;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Report implements Serializable, RowHolder {
	
	Logger log = LoggerFactory.getLogger(Report.class);

	private static final long serialVersionUID = 3116534669646550362L;

	private String title;
	private List<ReportRow> rows = new ArrayList<ReportRow>();
	
	@JsonIgnore
	private Map<String, Format> formatters = new HashMap<String, Format>();
	@JsonIgnore
	private String cellSeparator = ", ";
	@JsonIgnore
	private String rowSeparator = "\n";

	public Report() {
	}
	public Report(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCellSeparator() {
		return cellSeparator;
	}
	public void setCellSeparator(String cellSeparator) {
		this.cellSeparator = cellSeparator;
	}
	public String getRowSeparator() {
		return rowSeparator;
	}
	public void setRowSeparator(String rowSeparator) {
		this.rowSeparator = rowSeparator;
	}
	public List<ReportRow> getRows() {
		return rows;
	}
	public void setRows(List<ReportRow> rows) {
		this.rows = rows;
	}
	public RowHolder addRow(ReportRow rr) {
		rows.add(rr);
		return this;
	}
	public Map<String, Format> getFormatters() {
		return formatters;
	}
	public Report addFormatter(String key, Format f) {
		this.formatters.put(key, f);
		return this;
	}
	public void setFormatters(Map<String, Format> formatters) {
		this.formatters = formatters;
	}
	public void applyFormatters() {
		for(ReportRow row: rows) {
			for(ReportCell cell: row.getCells()) {
				if(cell.getValue()==null) continue;
				if(!formatters.containsKey(cell.getName())) continue;
				Format f = formatters.get(cell.getName());	
				try {
					String display = f.format(cell.getValue());
					cell.setDisplay(display);
				} catch (IllegalArgumentException e1) {
					log.error("Failed to format cell [" + cell.getName() + "]: " + e1.getMessage());
				}
			}
		}
	}
			

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(title != null) {
			sb.append("Title: " + title + "\n");
		}
		appendRows(sb, rows, 0);
		return sb.toString();
	}	
	
	private void appendRows(StringBuilder sb, List<ReportRow> rows, int level) {
		for(ReportRow row: rows) {
			String indent = " ".repeat(level*4);
			sb.append(indent + row.toString(cellSeparator) + rowSeparator);
			if(row.getRows().size() > 0) {
				appendRows(sb, row.getRows(), level + 1);
			}
		}
	}
}
