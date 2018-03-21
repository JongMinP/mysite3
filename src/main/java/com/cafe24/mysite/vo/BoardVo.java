package com.cafe24.mysite.vo;

import java.util.List;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private Long groupNo;
	private Long orderNo;
	private Long depth;
	private Long count;
	private String file;
	private String regDate;
	private UserVo user;

	private List<CommentVo> comments;

	public List<CommentVo> getComments() {
		return comments;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public void setComments(List<CommentVo> comments) {
		this.comments = comments;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Long getDepth() {
		return depth;
	}

	public void setDepth(Long depth) {
		this.depth = depth;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", groupNo=" + groupNo + ", orderNo="
				+ orderNo + ", depth=" + depth + ", count=" + count + ", file=" + file + ", regDate=" + regDate
				+ ", user=" + user + ", comments=" + comments + "]";
	}

}
