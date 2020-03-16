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
<c:import url="../header.jsp"/>
<div class="container-fluid">
    <c:choose>
        <c:when test="${ empty requestScope.album }">
            <div class="alert alert-danger">Vous n'avez pas encore ajouté d'album ! <a href="<c:url value="/user/add-album"/>" style="color: #0b0d14;font-style: italic;font-weight: bold">Cliquer ici pour ajouter</a></div>
        </c:when>
        <c:otherwise>
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Theme</th>
                    <th>Date d'ajout</th>
                    <th>Type d'album</th>
                    <th colspan="3" id="action" style="text-align: center">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.album}" var="album">
                    <tr>
                        <td><c:out value="${album.theme}"/></td>
                        <td><c:out value="${album.created_at.toLocaleString()}"/></td>
                        <td><c:out value="${album.type_album.type_album}"/></td>
                        <td style="text-align: center"><a href="<c:url value='/user/edit-album'/>?id=<c:out value="${album.id}"/>"> <i class="fa fa-edit"></i></a></td>
                        <td style="text-align: center"><a href="<c:url value='/user/detail-album'/>?id_album=<c:out value="${album.id}"/> "> <i class="fa fa-eye"></i></a></td>
                        <td style="text-align: center"><a href="<c:url value='/user/delete-album'/>?id=<c:out value="${album.id}"/>" onclick="deleteAlert(event)"> <i style="color: red" class="fa fa-times"></i></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>