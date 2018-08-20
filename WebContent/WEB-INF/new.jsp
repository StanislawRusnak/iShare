<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<div class="col-md-8 col-md-offset-2">
			<form class="form-signin" method="post" action="add">
				<h2 class="form-signin-heading">Dodaj nowe znalezisko</h2>
				<input name="inputName" type="text" class="form-control" placeholder="Co chcesz dodać?" required autofocus />
				<input name="inputUrl" type="text" name="inputUrl" class="form-control" placeholder="Podaj adres URL" required />
				<textarea name="inputDescription" rows="5" name="inputDescr"
					class="form-control" placeholder="Opis" required autofocus></textarea>
				<input class="btn btn-lg btn-primary btn-block" type="submit"
					value="Dodaj znalezisko" />
			</form>
		</div>
    </div>
    
   <jsp:include page="fragment/footer.jspf" />
    
    
   <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
   <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
   <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</html>