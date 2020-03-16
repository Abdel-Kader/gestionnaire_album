<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 09/03/2020
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="header.jsp"/>
<div class="main-panel">
    <div class="content-wrapper">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Theme</th>
                <th>Date d'ajout</th>
                <th>Propriétaire</th>
                <th>Type d'album</th>
                <th colspan="2" id="action" style="text-align: center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.albums}" var="album">
                <tr>
                    <td><c:out value="${album.theme}"/></td>
                    <td><c:out value="${album.created_at.toLocaleString()}"/></td>
                    <td><c:out value="${album.proprietaire.login}"/></td>
                    <td><c:out value="${album.type_album.type_album}"/></td>
                    <td><a href="<c:url value='edit-album'/>?id=<c:out value="${album.id}"/> "> <i class="fa fa-edit"></i></a></td>
                    <td><a href="<c:url value='delete-album'/>?id=<c:out value="${album.id}"/> "> <i style="color: red" class="fa fa-times"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<c:import url="footer.jsp"/>
