<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html>
<head>

<%
	pageContext.setAttribute("newLine", "\n");
%>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${ctx}/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${ctx}/guestbook/add" method="post">

					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" value=" "></td>
							<td>비밀번호</td>
							<td><input type="password" name="password" value=""></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>

					</table>
				</form>
				<ul>
					<li><c:set var="count" value="${fn:length(list) }" /> <c:forEach
							items="${list }" var="vo" varStatus="status">
							<table>
								<tr>
									<td>[${count - status.index }]
									</td>
									<td>${vo.name }</td>
									<td>${vo.dateTime }</td>
									<td><a
										href="${ctx}/guestbook/delete?no=${vo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
									${fn: replace(vo.content, newLine, "<br>") }
									</td>
								</tr>
							</table>
						</c:forEach> <br></li>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>