<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../css/loginform.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"   
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="   
	crossorigin="anonymous">
    </script>

</head>
<body>
    <section class="login-form">
        <h1>Login</h1>
        <c:if test="${NOT_LOGIN != null}">
        <div class="errmsg">
        	<label class="warning">
        		${NOT_LOGIN}
        	</label>
        </div>
        </c:if>
        <form action="<c:url value="/login/login.do"/>" method="POST">
            <div class="int-area">
                <input type="text" name="id" id="id" autocomplete="off" required>
                <label for="id">USERNAME</label>
            </div>
            <div class="int-area">
                <input type="password" name="pwd" id="pwd" autocomplete="off" required>
                <label for="pwd">PASSWORD</label>
            </div>
            <div class="btn-area">
                <button id="btn" type="submit">LOGIN</button>
            </div>
        </form>
        <div class="caption">
            <a href="" onclick="openPasswordResetPopup()">Forgot Password?</a>
        </div>
        <div class="caption">
            <a href="SignUpform.jsp">Sign up</a>
        </div>
    </section>
    <script>
        let id = $('#id');
        let pwd = $('#pwd');
        let btn = $('#btn');
	
        $(btn).on('click', function(){
            if($(id).val() ==""){
                $(id).next('label').addClass('warning');
                setTimeout(function(){
                    $('label').removeClass('warning')
                }, 3000);
            }
            else if($(pwd).val() == ""){
                $(pwd).next('label').addClass('warning');
            }
        });
        
		function openPasswordResetPopup() {
		    // 비밀번호 찾기 팝업 창 열기
		    window.open('http://localhost:8080/ZProj/loginout/PwdFinderForm.jsp', 'passwordResetWindow', 'width=500,height=600,location=no,status=no,scrollbars=yes');
		}
    </script>
</body>
</html>