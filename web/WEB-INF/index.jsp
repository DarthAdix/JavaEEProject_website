<%--
  Created by IntelliJ IDEA.
  User: AK
  Date: 26.04.2020
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <title>Website</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>

  <body>

  <jsp:include page="fragment/navbar.jspf" />

  <c:if test="${not empty requestScope.entries}">
    <c:forEach var="entry" items="${requestScope.entries}">
      <div class="container">
        <div class="row">
          <div class="col col-md-1 col-sm-2">
            <!-- Kolumna głosowania -->
            <a href="${pageContext.request.contextPath}/vote?entry_id=${entry.id}&vote=VOTE_UP" class="btn btn-block btn-primary btn-success"><span class="glyphicon glyphicon-arrow-up"></span> </a>
            <div class="well well-sm centered"><c:out value="${entry.upVote - entry.downVote}"/></div>
            <a href="${pageContext.request.contextPath}/vote?entry_id=${entry.id}&vote=VOTE_DOWN" class="btn btn-block btn-primary btn-danger"><span class="glyphicon glyphicon-arrow-down"></span> </a>
          </div>
          <div class="col col-md-11 col-sm-10">
            <!-- Kolumna z treścią -->
            <h3 class="centered"><a href="<c:out value="${entry.url}"/>"><c:out value="${entry.name}"/></a> </h3>
            <h6><small>Dodane przez: <c:out value="${entry.user.username}"/>,
              dnia: <fmt:formatDate value="${entry.timestamp}" pattern="dd/MM/YYYY"/></small></h6>
            <p><c:out value="${entry.description}"/></p>
            <a href="<c:out value="${entry.url}"/>" class="btn btn-default btn-xs">Przejdź do strony</a>
          </div>
        </div>
      </div>
    </c:forEach>
  </c:if>

  <jsp:include page="fragment/footer.jspf" />


  <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
  <script src="https://code.jquery.com/jquery-migrate-3.2.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
  </body>
</html>
