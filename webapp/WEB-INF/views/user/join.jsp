<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 태그 라이브러리 추가 -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- 폼 태그 라이브러리 추가  -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${ctx}/assets/css/user.css" rel="stylesheet" type="text/css">

<script src="${ctx}/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>

<script type="text/javascript">


	$(function() {
		$("#btn-checkemail").click(function() {
			var email = $("#email").val();
			if (email == "") {
				return;
			}
			// 데이터 타입 꼭 들어가야 된다.
			$.ajax({
				url : "${ctx}/api/user/checkemail?email=" + email,
				type : "get",
				data : "",
				dataType : "json",
				success : function(response) {
					// 				console.log(response);				
					if (response.result != "success") {
						console.log(response.message);
						return
					}

					if (response.data == "exist") {
						alert("이미 사용중인 이메일 입니다.");
						$("#email").val("").focus();
						return;
					}

					$("#img-check").show();
					$("#btn-checkemail").hide();

				},
				error : function(xhr, status, e) {
					console.error(status + ":" + e);
					// 개발단계에서만 

				}
			});

		});

		
		
		
		$("#email").change(function() {
			$("#img-check").hide();
			$("#btn-checkemail").show();
		});


		var name = $("#name");
		var email = $("#email");
		var password = $("#password");
		var agree = $("input:checkbox[id=agree-prov]").is(":checked");
// 		var agree = document.getElementById("agree-prov");
		var gender = document.getElementsByName("gender");

		$("#join-form").submit(function() {
			if (!/^[가-힝]{2,}$/.test(name.val())) {
				alert("한글로 2글자 이상을 넣으세요~");
				name.focus();
				return false;
			}

			if (!/^[\w]{4,}@[\w]+(\.[\w-]+){1,3}$/.test(email.val())) {
				alert("이메일 형식에 어긋납니다.");
				email.focus();
				return false;
			}

			if (!/^[a-z][a-z\d]{3,11}$/.test(password.val())) {
				alert("비밀번호는 영문 소문자 4자 ~ 12자로 이루어져야 한다.");
				password.focus();
				return false;
			}

			if(agree){
				alert("약관 동의를 해 주세요");
				return false;
			}
			
			if(!gender[0].checked && !gender[1].checked){
				alert("성별 체크 해주세요");
				return false;
			}
			
			
		

			return true;
		});

	});
</script>



</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<!-- 				클래스명에서 앞에 소문자 -->
				<form:form modelAttribute="userVo" id="join-form" name="joinForm"
					method="post" action="${ctx}/user/join">
					<label class="block-label" for="name">이름</label>

					<form:input path="name" id="name" />
					<p
						style="padding: 0; font-weight: bold; text-align: left; color: #f00;">
						<form:errors path="name" />
					</p>
					<!-- 				<input id="name" name="name" type="text" value=""> -->



					<%-- 					<spring:hasBindErrors name="userVo"> --%>
					<%-- 						<c:if test="${errors.hasFieldErrors('name') }"> --%>
					<!-- 							<p style="padding: 0; text-align: left; color: #f00;"> -->
					<%-- 								<strong>${errors.getFieldError( 'name' ).defaultMessage }</strong> --%>
					<!-- 							</p> -->
					<!-- 														이거 문제 점은 틀린것이 있으면 맞은것도 사라짐 -->

					<%-- 						</c:if> --%>
					<%-- 					</spring:hasBindErrors> --%>


					<label class="block-label" for="email">이메일</label>
					<!-- 					<input type="text" id ="email" value=""> -->
					<form:input path="email" id="email" />
					<p
						style="padding: 0; font-weight: bold; text-align: left; color: #f00;">
						<form:errors path="email" />
					</p>

					<!-- 					<input id="email" name="email" type="text" value=""> -->
					<img id='img-check' style="display: none;"
						src="${ctx}/assets/images/check.png">
					<input type="button" value="id 중복체크" id="btn-checkemail">
					<label class="block-label">패스워드</label>
					<form:password path="password" id="password" />

					<!-- 				<input name="password" type="password" value=""> -->

					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('password') }">
							<p style="padding: 0; text-align: left; color: #f00;">
								<spring:message
									code="${errors.getFieldError('password').codes[0] }"
									text="${errors.getFieldError('password').defaultMessage }" />
								<%-- 								<strong>${errors.getFieldError( 'password' ).defaultMessage }</strong> --%>
							</p>
						</c:if>
					</spring:hasBindErrors>


					<fieldset>
						<legend>성별</legend>
						<label>여</label>
						<form:radiobutton path="gender" value="female" name="gender"  />
						<!-- 						<input type="radio" name="gender" value="female" checked="checked">  -->
						<label>남</label>
						<form:radiobutton path="gender" value="male" name="gender" />
						<!-- 						<input type="radio" name="gender" value="male"> -->
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>