package com.github.dantebarba.queryfork.core;

public class BuiltInPaginator implements Paginator {

	public static int DEFAULT_PAGE_SIZE = 20;

	private int count = 0;
	private int first = 0;

	@Override
	public int getFirst() {
		return first;
	}

	@Override
	public int getPageSize() {
		return DEFAULT_PAGE_SIZE;
	}

	@Override
	public int getCount() {
		return count;
	}

	public boolean hasNextPage() {
		return this.getFirst() + this.getPageSize() < this.getCount();
	}
	
	public boolean hasPreviousPage() {
		return this.getFirst() - this.getPageSize() >= 0;
	}
	
	public boolean isFirstPage() {
		return this.getFirst() == 0;
	}

}
