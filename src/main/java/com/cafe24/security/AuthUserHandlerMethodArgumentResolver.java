package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		
		if(this.supportsParameter(parameter) == false ) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		
		System.out.println("들어오니1");
		// @AuthUser가 붙어 있고 파라미터 타입이 UserVo
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
			
		if(session == null) {
			return null;
		}
		System.out.println("들어오니2");

		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		//1. @AuthUser 가 붙어 있는지 확인
		AuthUser authUser =  parameter.getParameterAnnotation(AuthUser.class);
		System.out.println("들어오니0");

		
		//2. @AuthUser 가 안 붙어 있음
		if(authUser == null) {
			return false;
		}
		
		//3. Type이 UserVo가 아님
		if(parameter.getParameterType().equals(UserVo.class) == false) {
			
			return false;
		}
		
		
		return true;
	}


}
