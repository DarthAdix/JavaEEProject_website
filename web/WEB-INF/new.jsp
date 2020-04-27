<%--
  Created by IntelliJ IDEA.
  User: AK
  Date: 26.04.2020
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Website - dodaj wpis</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>

<jsp:include page="fragment/navbar.jspf" />

<div class="container">
    <div class="col-md-8 col-md-offset-2">
        <form class="form-signin" action="add" method="post">
            <h2 class="form-signin-heading">Dodaj wpis</h2>
            <input name="inputName" type="text" class="form-control" placeholder="Nazwa wpisu" required autofocus>
            <input name="inputUrl" type="url" class="form-control" placeholder="URL" required>
            <textarea name="inputDescription" rows="5" class="form-control" placeholder="Opis" required autofocus></textarea>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj</button>
        </form>
    </div>
</div>

<jsp:include page="fragment/footer.jspf" />

<script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
<script src="https://code.jquery.com/jquery-migrate-3.2.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
