<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 21/02/2020
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gestionnaire d'album</title>
<body>
<c:import url="header.jsp"/>
<div class="container-fluid">

    <div class="row text-center text-lg-left">
        <c:forEach items="${requestScope.album}" var="album">
            <div class="col-md-3 grid-margin stretch-card">
                <div class="card" id="wrapper" >
                    <img class="img-fluid img-thumbnail" src="<c:url value="/images/folder.jpg"/>" alt="">
                    <div class="card-body" style="text-align: center">
                        <strong style="font-size: 20px"><a href="<c:url value="/list-photo"/>?album=<c:out value="${album['id']}"/>"><c:out value="${album['theme']}" /></a></strong>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
</div>
</body>
</html>
