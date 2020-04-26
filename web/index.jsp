<%--
  Created by IntelliJ IDEA.
  User: AK
  Date: 26.04.2020
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Website</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>

  <body>

  <nav class="nav navbar navbar-inverse navbar-fixed-top">
    <div class="container">
      <a href="#" class="navbar-brand">Website</a>

      <button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
        <span class="glyphicon glyphicon-list"></span>
      </button>

      <div class="collapse navbar-collapse navHeaderCollapse">
        <ul class="nav navbar-nav navbar-right">
          <li class="active"><a href="#">Strona główna</a> </li>
          <li><a href="#">Dodaj</a> </li>
          <li><a href="#">Zaloguj się</a> </li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="container">
    <div class="row">
      <div class="col col-md-1 col-sm-2">
        <!-- Kolumna głosowania -->
        <a href="#" class="btn btn-block btn-primary btn-success"><span class="glyphicon glyphicon-arrow-up"></span> </a>
        <div class="well well-sm centered">Votes</div>
        <a href="#" class="btn btn-block btn-primary btn-danger"><span class="glyphicon glyphicon-arrow-down"></span> </a>
      </div>
      <div class="col col-md-11 col-sm-10">
        <!-- Kolumna z treścią -->
        <h3 class="centered"><a href="#">Wpis</a> </h3>
        <h6><small>Dodane przez: ###, dnia: ###</small></h6>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nec suscipit nibh, ac facilisis metus. Quisque
          ligula risus, commodo tincidunt nisi eu, auctor suscipit eros. Sed egestas tristique finibus. Aenean sed
          venenatis sapien, sed porta leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec odio est,
          dictum nec enim nec, lobortis tempus arcu. Vestibulum mi diam, tempor vel luctus vestibulum, pellentesque
          a justo. Donec tincidunt eu ante id efficitur. Curabitur venenatis ante non ligula tempus, nec accumsan lectus
          dignissim. Pellentesque mattis eget purus nec blandit. In ornare efficitur felis, id venenatis magna auctor ut.
          Nunc dignissim tellus.</p>
        <a class="btn btn-default btn-xs">Przejdź do strony</a>
      </div>
    </div>
  </div>

  <footer class="footer">
    <div class="container">
      <p class="navbar-text">Website - developed by Adam Kowalczyk </p>
    </div>
  </footer>

  <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
  <script src="https://code.jquery.com/jquery-migrate-3.2.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
  </body>
</html>
