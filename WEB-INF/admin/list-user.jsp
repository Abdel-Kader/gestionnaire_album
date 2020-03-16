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
                <th>Nom</th>
                <th>Prénom</th>
                <th>Login</th>
                <th>Contact</th>
                <th>D'ate d'inscription</th>
                <th>Profil</th>
                <th colspan="3" style="text-align: center">Actions</th>
            </tr>
            </thead>
            <tbody>
          <c:forEach items="${requestScope.utilisateurs}" var="utilisateur">
              <tr>
                  <td><c:out value="${utilisateur.nom}"/></td>
                  <td><c:out value="${utilisateur.prenom}"/></td>
                  <td><c:out value="${utilisateur.login}"/></td>
                  <td><c:out value="${utilisateur.contact}"/></td>
                  <td><c:out value="${utilisateur.created_at.toLocaleString()}"/></td>
                  <td><c:out value="${utilisateur.type_utilisateur.type_utilisateur}"/></td>
                  <td style="text-align: center"><a href="<c:url value='edit-user'/>?id=<c:out value="${utilisateur.id}"/> "> <i class="fa fa-edit"></i></a></td>
                  <td style="text-align: center"><a href="<c:url value='detail-user'/>?id=<c:out value="${utilisateur.id}"/> "> <i style="color: green" class="fa fa-eye"></i></a></td>
                  <td style="text-align: center"><a href="<c:url value='delete-user'/>?id=<c:out value="${utilisateur.id}"/> "> <i style="color: red" class="fa fa-times"></i></a></td>
              </tr>
          </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<c:import url="footer.jsp"/>
