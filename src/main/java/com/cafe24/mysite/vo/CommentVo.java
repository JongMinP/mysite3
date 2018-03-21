package com.cafe24.mysite.vo;

public class CommentVo {
	private Long no;
	private String content;
	private String regDate;
	private UserVo user;

	private Long boardNo;

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", content=" + content + ", regDate=" + regDate + ", user=" + user + ", boardNo="
				+ boardNo + "]";
	}

}
