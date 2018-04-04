<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<div id="navigation">
	<ul>

		<c:choose>
			<c:when test="${param.menu == 'main' }">
				<li class="selected"><a href="${ctx}/main">박종민</a></li>
				<li><a href="${ctx}/guestbook/list">방명록</a></li>
				<li><a href="${ctx}/guestbook/ajax">방명록(ajax)</a></li>
				<li><a href="${ctx}/board/list">게시판</a></li>
				<li><a href="${ctx}/gallery">갤러리</a></li>
			</c:when>
			<c:when test="${param.menu == 'guestbook' }">
				<li><a href="${ctx}/main">박종민</a></li>
				<li class="selected"><a href="${ctx}/guestbook/list">방명록</a></li>
				<li><a href="${ctx}/guestbook/ajax">방명록(ajax)</a></li>
				<li><a href="${ctx}/board/list">게시판</a></li>
				<li><a href="${ctx}/gallery">갤러리</a></li>
			</c:when>
			<c:when test="${param.menu == 'guestbook-ajax' }">
				<li><a href="${ctx}/main">박종민</a></li>
				<li><a href="${ctx}/guestbook/list">방명록</a></li>
				<li class="selected"><a href="${ctx}/guestbook/ajax">방명록(ajax)</a></li>
				<li><a href="${ctx}/board/list">게시판</a></li>
				<li><a href="${ctx}/gallery">갤러리</a></li>
			</c:when>
			
			<c:when test="${param.menu == 'board' }">
				<li><a href="${ctx}/main">박종민</a></li>
				<li><a href="${ctx}/guestbook/list">방명록</a></li>
				<li><a href="${ctx}/guestbook/ajax">방명록(ajax)</a></li>
				<li class="selected"><a href="${ctx}/board/list">게시판</a></li>
				<li><a href="${ctx}/gallery">갤러리</a></li>
			</c:when>
			
			<c:when test="${param.menu == 'gallery' }">
				<li><a href="${ctx}/main">박종민</a></li>
				<li><a href="${ctx}/guestbook/list">방명록</a></li>
				<li><a href="${ctx}/guestbook/ajax">방명록(ajax)</a></li>
				<li><a href="${ctx}/board/list">게시판</a></li>
				<li class="selected"><a href="${ctx}/gallery">갤러리</a></li>
			</c:when>
			
			<c:otherwise>
				<li><a href="${ctx}/main">박종민</a></li>
				<li><a href="${ctx}/guestbook/list">방명록</a></li>
				<li><a href="${ctx}/guestbook/ajax">방명록(ajax)</a></li>
				<li><a href="${ctx}/board/list">게시판</a></li>
				<li><a href="${ctx}/gallery">갤러리</a></li>
			</c:otherwise>

		</c:choose>
	</ul>
</div>