package com.cafe24.mysite.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {

	private Long no;
	
	// 비면 안되고, 글자수 최소 2, 최대 5
	@NotEmpty
	@Length(min=2,max =5)
	private String name;
	
	@Email
	@NotEmpty
	//@PhonNumber
//	@Pattern(regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")
	private String email;
	
	@NotEmpty
	@Pattern(regexp="^[0-9a-zA-Z]{4,12}$") // 0~9,a~z,A~Z 의 문자로 이루어진 4이상 12이하 
	private String password;
	
	@Pattern(regexp = "^(female|male$)")  // female , male 만
	private String gender;
	
	private String joinDate;

	public UserVo() {
	}

	public UserVo(Long no) {
		this.no = no;
	}

	public UserVo(Long no, String name) {
		this.no = no;
		this.name = name;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", joinDate=" + joinDate + "]";
	}

}
