package com.cafe24.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

	public enum Role {ADMIN,USER}
	
	public Role role() default Role.USER;
	
//	public String value() default "user";
//	public int test() default 1;
	
	
}
