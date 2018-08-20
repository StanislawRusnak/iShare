<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
	<title>Register in iShare</title>
</head>
<body>
    <jsp:include page="fragment/navbar.jspf" />
    
    <div class="container">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<form class="form-signin" method="post" action="register">
				<h2 class="form-signin-heading">Zarejestruj się</h2>
				<input name="inputEmail" type="email" class="form-control" placeholder="Email" required autofocus />
				<input name="inputUsername" type="text" class="form-control" placeholder="Nazwa użytkownika" required autofocus />
				<input name="inputPassword" type="password" class="form-control" placeholder="Hasło" required />
				<button class="btn btn-lg btn-primary btn-block" type="submit" >Zarejestruj</button>
			</form>
		</div>
    </div>
    
    <jsp:include page="fragment/footer.jspf" />
    
    
   <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
   <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
   <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</html>