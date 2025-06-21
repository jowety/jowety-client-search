package com.jowety.data.client.search;

import java.util.List;

public interface RowHolder {

	public RowHolder addRow(ReportRow rr) ;
	public List<ReportRow> getRows();
	public void setRows(List<ReportRow> rows) ;
}
