<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${ctx}/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${ctx}/board/list" method="get">
					 <input type="text" id="kwd" name="kwd" value=""> 
					 <input type="submit" value="찾기">
				</form>


				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(boards) }" />
					<c:forEach items="${boards }" var="board" varStatus="status">

						<tr>
							<td>${pager.totalCount - (pager.page - 1)*pager.pagingSize - status.index}</td>
							<td style="text-align:left; padding-left:${20*board.depth}px">
								<c:if test="${board.depth > 0 }">
									<img src="${ctx}/assets/images/reply.png" />
								</c:if> <a href="${ctx}/board/view?no=${board.no }">${board.title}</a>
							</td>
							<td>${board.user.name }</td>
							<td>${board.count }</td>
							<td>${board.regDate }</td>
							<td><c:if test="${board.user.no eq authUser.no }">
									<a href="${ctx}/board/delete?no=${board.no }" class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>

				</table>
				<div class="pager">
					<ul>

						<c:choose>
							<c:when test="${pager.prev }">
								<li style="color: black;"><a
									href="${ctx}/board/list?page=${(pager.blockPage-1) * pager.pageSize}&kwd=${kwd}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li style="color: #EAEAEA;">◀</li>
							</c:otherwise>
						</c:choose>

						<c:forEach begin="${pager.pageStart }"
							end="${pager.pageStart + pager.pagingSize - 1 }" var="page">
							<c:choose>
								<c:when test="${pager.pageEnd < page }">
									<li>${page }</li>
								</c:when>
								<c:when test="${pager.page == page }">
									<li class="selected">${page }</li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath }/board/list?page=${page }&kwd=${kwd}">${page }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${pager.next }">
								<li style="color: black;"><a
									href="${ctx}/board/list?page=${pager.blockPage * pager.pageSize + 1  }&kwd=${kwd}">▶</a></li>
							</c:when>
							<c:otherwise>
								<li style="color: #EAEAEA;">▶</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href="${ctx}/board/write" id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>