<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
// 	String result = (String) request.getAttribute("result");
// 	String email = (String) request.getAttribute("email");
%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/user.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post"
					action="${pageContext.servletContext.contextPath}/user/login">
					<label
						class="block-label" for="email">이메일</label> <input id="email"
						name="email" type="text"
						value="${email eq null ? '' : email }"> <label
						class="block-label">패스워드</label> <input name="password"
						type="password" value="">

					<%-- 					<%if("fail".equals(result)) {%> --%>
					<c:if test="${'fail' eq  result }">
						<p>로그인이 실패 했습니다.</p>
					</c:if>
					<%-- 					<%} %>	 --%>
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>