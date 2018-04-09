<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />

<div id="header">
	<h1>
		<a href="${ctx}/main">MySite</a>
	</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${ctx}/user/login">로그인</a></li>
				<li><a href="${ctx}/user/join">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${ctx}/user/modify">회원정보수정</a></li>
				<li><a href="${ctx}/user/logout">로그아웃</a></li>
				<li>${authUser.name }님안녕하세요^^;</li>
			</c:otherwise>
		</c:choose>

	</ul>
</div>