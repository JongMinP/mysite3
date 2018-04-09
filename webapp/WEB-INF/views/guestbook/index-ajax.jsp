<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>




<script>
	var isEnd = false;
	var messageBox = function(title, message, callback) {

		$("#dialog-message").attr("title", title);
		$("#dialog-message p").text(message);

		$("#dialog-message").dialog({
			modal : true,
			buttons : {
				"확인" : function() {
					$(this).dialog("close");
				}
			},
			// 콜백이 널이면 뒤에 함수 에러 처리
			close : callback || function() {
			}
		});

	};

	var render = function(mode, vo) {

		var html = "<li data-no='" + vo.no + "'>" + "<strong>" + vo.name
				+ "</strong>" + "<p>" + vo.content.replace(/\n/gi, "<br>")
				+ "</p>" + "<strong></strong>"
				+ "<a href='#' data-no='" + vo.no +"'>삭제</a>" + "</li>";

		if (mode == true) {
			$("#list-guestbook").prepend(html);

		} else {
			$("#list-guestbook").append(html);

		}

		// 		$("#list-guestbook")[mode ? "prepend" : "append"](html);

	}

	$(function() {

		// 삭제 시 비밀번호 입력 다이알 로그
		var deleteDialog = $("#dialog-delete-form").dialog({
					autoOpen : false,
					modal : true,
					buttons : {
						"삭제" : function() {
							var password = $("#password-delete").val();
							var no = $("#hidden-no").val();

							console.log(password + ":" + no);

							// ajax 통신

							$.ajax({
								url : "/mysite3/api/guestbook/delete",
								type : "post",
								dataType : "json",
								data : "no=" + no + "&password=" + password,
								success : function(response) {
									if (response.result == "fail") {
										console.log(responsne.message);
										return;
									}

									if (response.data == -1) {
										$(".validateTips.normal").hide();
										$(".validateTips.error").show();
										$("#password-delete").val("");

										return;
									}
									$("#list-guestbook li[data-no=" + response.data + "]").remove();
									deleteDialog.dialog("close");

								}
							});

						},
						"취소" : function() {
							deleteDialog.dialog("close");
						}
					},
					close : function() {
							$("#password-delete").val("");
							$("#hidden-no").val("");
							$(".validateTips.normal").show();
							$(".validateTips.error").hide();
					}
				});

		// Live Event Listener 
		$(document).on("click", "#list-guestbook li a", function(event) {
			event.preventDefault();

			var no = $(this).data("no");
			$("#hidden-no").val(no);

			deleteDialog.dialog("open");

		});
		
		

		$("#add-form").submit(function(event) {
			event.preventDefault();// 이벤트 ?방지
			// 이름 까지 전부 나옴 알아서 직렬화
			var queryString = $(this).serialize();
			// 			console.log(queryString);

			var data = {};
			$.each($(this).serializeArray(), function(index, o) {
				data[o.name] = o.value;

			});

			if (data["name"] == '') {
				messageBox("메세지 등록", "이름이 비어 있습니다.", function() {
					$("#input-name").focus();
				});
				return;
			}

			if (data["password"] == '') {
				messageBox("메세지 등록", "비밀번호가 비어 있습니다.", function() {
					$("#input-password").focus();
				});
				return;
			}
			if (data["content"] == '') {
				messageBox("메세지 등록", "내용이 비어 있습니다.", function() {
					$("#tx-content").focus();
				});
				return;
			}

			console.log(data);

			// data 내가 받는 타입 
			// contentType 보내는 타입

			$.ajax({
				url : "/mysite3/api/guestbook/insert",
				type : "post",
				dataType : "json",
				contentType : "application/json",
				data : JSON.stringify(data), // json으로 받는거
				success : function(response) {
					render(true, response.data);
					// 하나만 리셋 
					// 전부다 리셋 하려면 for문으로 돌려
					$("#add-form")[0].reset();
				}
			});

			// 이걸로 하려면 modelAttribute
			// 			$.ajax({
			// 				url : "/mysite3/api/guestbook/insert",
			// 				type : "post",
			// 				dataType : "json",
			// 				data : queryString,
			// 				success : function(response) {
			// 					render(true, response.data);
			// 					$("#add-form")[0].reset();
			// 				}
			// 			});

		});

		
		$("#btn-fetch").click(function() {

			if (isEnd == true) {
				return;
			}
			// 빈거 일수는 있어도 null은 불가
			// .data에서 null이 될 수는 있다.
			// || 연산자 사용 null이 될경우 false가 되어 뒤에 있는 값을 넣어 준다.
			// 자바스크립트에서만 먹는다.
			var startNo = $("#list-guestbook li").last().data("no") || 0;
			// 맨처음은 없기 때문에 0 이 들어가서 맨 처음 값을 불러온다.
			// 두 번째에는 가장 마지막 자식의 data-no(vo.no)를 불러 와서 그 no보다 작은 애들을 리턴 
			$.ajax({
				url : "/mysite3/api/guestbook/list?no=" + startNo,
				type : "get",
				dataType : "json",
				success : function(response) {
					// 				console.log(response);
					// 성공 유무 
					if (response.result != "success") {
						console.log(response.message);
						return;
					}

					//끝 감지
					if (response.data.length < 5) {
						isEnd = true;
						$("#btn-fetch").prop("disabled", true);
					}

					// render
					// 정규 표현식  \n 전부 
					$.each(response.data, function(index, vo) {
						render(false, vo);

					});

					
					// 위에서랑 같은 처리
					// 5개씩이니 4번째가 마지막
					var length = response.data.length;
					if (length > 0) {
						startNo = response.data[length - 1];
					}
					
					
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
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" name="name" placeholder="이름">
					<input type="password" id="input-password" name="password"
						placeholder="비밀번호">
					<textarea id="tx-content" name="content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>

				<hr>
				<ul id="list-guestbook">



				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
				<p class="validateTips error" style="display: none">비밀번호가 틀립니다.</p>
				<form>
					<input type="password" id="password-delete" value=""
						class="text ui-widget-content ui-corner-all"> <input
						type="hidden" id="hidden-no" value=""> <input
						type="submit" tabindex="-1"
						style="position: absolute; top: -1000px">
				</form>
			</div>

			<button id="btn-fetch">가져오기</button>
			<div id="dialog-message" title="" style="display: none">
				<p></p>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>