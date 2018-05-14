package com.cafe24.pager;

public class Pager {

	/**
	 * page : 현재 페이지 번호 
	 * totalCount : 전체 게시물의 수 
	 * prev : 네비게이션(왼쪽 화살표)의 유무 
	 * next :네비게이션(오른쪽 화살표)의 유무
	 * pageStart : 페이지의 시작 pageEnd : 페이지의 마지막 
	 * blockPage : 현재 블락번호 
	 * pageCount : 전체 개수에서 페이징 수만큼 나눈 페이지의 개수 
	 * blockCount : 페이지의 개수에서 페이지 수만큼 나눈 블락의 개수 
	 * pagingSize : 한 번에 보여지는 게시물의 숫자 (sql에서 limit 두 번째 숫자 부분) 
	 * pageSize : 몇 개씩 페이지 되어 있는 번호 
	 * sqlStartPage : sql에서 limit 시작 하는 번호
	 */

	private int page;
	private int totalCount;

	private boolean prev;
	private boolean next;

	private int pageStart;
	private int pageEnd;

	private int blockPage;

	private int pageCount;
	private int blockCount;

	private int pagingSize;
	private int pageSize;

	private int sqlStartPage;

	public Pager() {

		this.pageSize = 5;
		this.pagingSize = 5;
		this.page = 1;
		this.prev = false;
		this.next = false;
		this.pageStart = 1;
		this.blockPage = 1;

	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getPagingSize() {
		return pagingSize;
	}

	public void setPagingSize(int pagingSize) {
		this.pagingSize = pagingSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getSqlStartPage() {
		return sqlStartPage;
	}

	public void setSqlStartPage(int sqlStartPage) {
		this.sqlStartPage = sqlStartPage;
	}

	public void pagination(int page, int totalCount) {
		this.totalCount = totalCount;

		// 페이지 음수의 경우 1로 초기화
		this.page = (page < 1) ? 1 : page;

		pageCount = (int) Math.ceil((double) totalCount / pagingSize);
		blockCount = (int) Math.ceil((double) pageCount / pageSize);

		// 페이지 최대 페이지 넘는 경우 최대로 초기화
		this.page = (page > pageCount) ? pageCount : page;
		
		// 페이지가 0일 경우 1로 초기화
		this.page = (this.page ==0 ) ? 1 : this.page;

		int temp = (int) Math.ceil((double) this.page / pageSize);
		blockPage = temp > 0 ? temp : 1;

		pageStart = (blockPage - 1) * pageSize + 1;

		prev = blockPage <= 1 ? false : true;

		next = blockPage < blockCount ? true : false;

		pageEnd = (next) ? (pageStart - 1) + pageSize : pageCount;

		sqlStartPage = (this.page - 1) * pageSize;

	}

	@Override
	public String toString() {
		return "Pager [page=" + page + ", totalCount=" + totalCount + ", prev=" + prev + ", next=" + next
				+ ", pageStart=" + pageStart + ", pageEnd=" + pageEnd + ", blockPage=" + blockPage + ", pageCount="
				+ pageCount + ", blockCount=" + blockCount + ", pagingSize=" + pagingSize + ", pageSize=" + pageSize
				+ ", sqlStartPage=" + sqlStartPage + "]";
	}

}
