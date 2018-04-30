<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${ctx}/assets/css/board.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${board.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">${fn: replace(board.content, newLine,"<br>") }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${ctx}/board/list">글목록</a>

					<c:if test="${board.user.no eq authUser.no }">
						<a
							href="${ctx}/board/modify?no=${board.no }">글수정</a>
					</c:if>
					<c:if test="${not empty authUser }">
						<a
							href="${ctx}/board/reply?no=${board.no }">답글</a>
					</c:if>
				</div>
			</div>
			<div id="board" class="board-form">
				<form class="board-form" method="post"
					action="${ctx}/comment/insert">
					<input type="hidden" name="a" value="comment"> <input
						type="hidden" name="boardNo" value="${board.no }" />
					
					<table class="tbl-ex">
						<tr>
							<th colspan="4">댓글</th>
						</tr>
						<c:forEach items="${board.comments }" var="comment">
							<tr>
								<td class="label">${comment.user.name }</td>
								<td>${fn: replace(comment.content, newLine,"<br>") }</td>
								<td style="text-align: right;">${comment.regDate }</td>
								<c:if test="${comment.user.no eq authUser.no }">
									<td><a href="${ctx}/comment/delete?no=${comment.no }&boardNo=${board.no}"
										class="del">삭제</a></td>
								</c:if>

							</tr>
						</c:forEach>

					</table>
					<textarea style="width: 530px; height: 100px;" id="content"
						name="content">
						
						</textarea>
					<div class="bottom">
						<c:if test="${not empty authUser }">
							<input type="submit" value="등록">
						</c:if>
					</div>
				</form>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>