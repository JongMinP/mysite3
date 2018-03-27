package com.cafe24.security;

import javax.annotation.Resource.AuthenticationType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1. handler 종류 확인
		if (handler instanceof HandlerMethod == false) {
			// 디폴트 핸들러 인 경우
			return true; // 뒤로
		}
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		// auth.role();

		// -- 클래스 타입 어스 받아오기
		if (auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		// 4. Method에 @Auth가 없는 경우
		if (auth == null) {
			return true; // 뒤로
		}

		// 5. @Auth 가 붙어 있는 경우 , 인증여부 체크
		HttpSession session = request.getSession();

		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		Auth.Role role = auth.role();

		if (role == Auth.Role.USER) { // 인증 되어있기 때문 허가
			return true;
		}

		// 6. 접근 허가

		return true;

	}

}
