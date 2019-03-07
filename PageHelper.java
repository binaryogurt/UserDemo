package com.user.page;

import java.util.ArrayList;
import java.util.List;

public class PageHelper<T> {

	//数据集合
	private List<T> rows = new ArrayList<T>();
	
	//数据总数
	private int total;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
