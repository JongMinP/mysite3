<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />


<div id="navigation">
	<ul>
		<li id = "mainJ"><a href="${ctx}/main">박종민</a></li>
		<li id = "guestbookJ"><a href="${ctx}/guestbook/list">방명록</a></li>
		<li id = "guestbook-ajaxJ"><a href="${ctx}/guestbook/ajax">방명록(ajax)</a></li>
		<li id = "boardJ"><a href="${ctx}/board/list">게시판</a></li>
		<li id = "galleryJ"><a href="${ctx}/gallery">갤러리</a></li>

	</ul>
</div>


<script>
	$(function() {

		$("#navigation ul li[class='selected']").attr("class", "");
		$("#navigation ul li[id='${param.menu}J']").attr("class", "selected");

	});
</script>