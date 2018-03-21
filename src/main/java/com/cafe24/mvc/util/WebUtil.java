package com.cafe24.mvc.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	public static String checkParameter(String value, String defaultValue) {
			
		return defaultValue;
	}

	public static Long checkParameter(String value, Long defaultValue) {

		return defaultValue;
	}

	public static Integer checkParameter(String value, Integer defaultValue) {

		return defaultValue;
	}

	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		response.sendRedirect(url);
	}

	public static void foward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher(path);

		rd.forward(request, response);

	}

}
