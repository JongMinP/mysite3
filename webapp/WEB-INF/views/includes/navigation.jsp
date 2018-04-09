<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />


<script type="text/javascript">

	$(function() {

		var menu = $("#menu");

		if (menu.val() == 'main') {
			$("li:nth-child(1)").addClass("selected");

		} else if (menu.val() == 'guestbook') {
			$("li:nth-child(2)").addClass("selected");

		} else if (menu.val() == 'guestbook-ajax') {
			$("li:nth-child(3)").addClass("selected");

		} else if (menu.val() == 'board') {
			$("li:nth-child(4)").addClass("selected");

		} else if (menu.val() == 'gallery') {
			$("li:nth-child(5)").addClass("selected");
		}


	});
</script>
<input type="hidden" id="menu" value="${param.menu}" />
<div id="navigation">
	<ul>
		<li><a href="${ctx}/main">박종민</a></li>
		<li><a href="${ctx}/guestbook/list">방명록</a></li>
		<li><a href="${ctx}/guestbook/ajax">방명록(ajax)</a></li>
		<li><a href="${ctx}/board/list">게시판</a></li>
		<li><a href="${ctx}/gallery">갤러리</a></li>

	</ul>
</div>