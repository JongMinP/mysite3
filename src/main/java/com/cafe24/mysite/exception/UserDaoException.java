package com.cafe24.mysite.exception;

public class UserDaoException extends RuntimeException {
	
	public UserDaoException() {
		super("UserDaoException Occurs");
	}

	public UserDaoException(String message) {
		super("UserDaoException Occurs" + message);
	}
}
