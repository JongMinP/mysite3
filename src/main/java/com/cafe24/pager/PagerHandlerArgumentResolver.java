package com.cafe24.pager;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PagerHandlerArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		if(supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		System.out.println("이건되니?");
		Pager pager = new Pager();
		
		return pager;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		// 어노테이션 Page 여부 검사
		Page page = (Page) parameter.getParameterAnnotation(Page.class);

		// 널 체크
		if (page == null) {
			return false;
		}
		// 타입 검사
		if (parameter.getParameterType().equals(Pager.class) == false) {
			return false;
		}

		return true;
	}

}
