<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${ctx}/assets/css/user.css"
	rel="stylesheet" type="text/css">

<script
	src="${ctx}/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript" ></script>

<script type="text/javascript">
$(function() {
	$("#btn-checkemail").click(function () { 
		var email =  $("#email").val();
		if(email == "") {
			return;
		}
		// 데이터 타입 꼭 들어가야 된다.
		$.ajax({
			url: "${ctx}/api/user/checkemail?email=" + email,
			type: "get",
			data: "" ,
			dataType : "json",  
			success: function(response) {
// 				console.log(response);				
				if(response.result != "success") {
					console.log(response.message);
					return
				}
				
				if(response.data == "exist") {
					alert("이미 사용중인 이메일 입니다.");
					$("#email").val("").focus();
					return;
				}
				
				$("#img-check").show();
				$("#btn-checkemail").hide();
				
			},
			error: function(xhr,status, e) {
				console.error(status + ":" + e);
				// 개발단계에서만 
				
			}
		});
		
	});
});

</script>



</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post"
					action="${ctx}/user/join">
					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value=""> <label
						class="block-label" for="email">이메일</label> <input id="email"
						name="email" type="text" value=""> 
						
						<img id ='img-check' style="display: none;"  src="${ctx}/assets/images/check.png" >
						
						<input type="button"value="id 중복체크" id="btn-checkemail"> <label class="block-label">패스워드</label> <input
						name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>