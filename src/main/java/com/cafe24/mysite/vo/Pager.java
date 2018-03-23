package com.cafe24.mysite.vo;

public class Pager {

	private int page; // 몇 번째 페이지 뷰 (1페이지가 1~5번 페이지 까지)
	private int pageNum; // 몇 개씩 페이지 나눌지

	private int pageStart; // 시작
	private int pageEnd; // 끝

	private boolean prev; // 왼쪽 화살표 
	private boolean next; // 오른쪽 화살표

	private int currentPage; // 현재 페이지

	private int totalCount; // 총 게시글 수
	private int indexCount; // 게시글의 숫자

	public Pager() {
		this.page = 1;
		this.pageNum = 10;
		this.pageStart = 1;
		this.pageEnd = 5;
		this.prev = false;
		this.next = false;
		this.currentPage = 1;
	}

	public int getPage() {
		return page;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPage(int page) {

		if (page <= 0) {
			this.page = 1;
			return;
		}

		pageStart = page * 5 - 4;

		pageEnd = page * 5;

		this.page = page;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageStart() {

		return this.pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {

		if (totalCount < pageEnd * 10) {
			pageEnd = (int) (Math.ceil(totalCount / 10.0));
			if (pageEnd == 0)
				pageEnd = 1;
		}

		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public boolean isPrev() {

		if (pageStart > 1) {
			return true;
		}
		return false;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {

		if (pageEnd * 10 >= totalCount) {
			return false;
		}

		return true;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getIndexCount() {
		return indexCount;
	}

	public void setIndexCount(int indexCount) {
		this.indexCount = indexCount;
	}

	@Override
	public String toString() {
		return "Pager [page=" + page + ", pageNum=" + pageNum + ", pageStart=" + pageStart + ", pageEnd=" + pageEnd
				+ ", prev=" + prev + ", next=" + next + ", currentPage=" + currentPage + ", totalCount=" + totalCount
				+ "]";
	}

}
