<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 12/03/2020
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Détail de l'album</title>
</head>
<body>
<c:import url="../header.jsp"/>
<link href="<c:url value='/css/detail.css'/>" rel="stylesheet" type="text/css">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg-9">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-lg-3">
                                Thème :
                            </div>
                            <div class="col-lg-9">
                                <strong><c:out value="${requestScope.album.theme}"/></strong>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-lg-3">
                                Créé le :
                            </div>
                            <div class="col-lg-9">
                                <strong><c:out value="${requestScope.album.created_at.toLocaleString()}"/></strong>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-lg-3">
                                Type d'album :
                            </div>
                            <div class="col-lg-9">
                                <c:if test="${requestScope.album.type_album.id == 2}">
                                     <strong style="color: #10dc60">Ouvert à tout le monde !</strong>
                                </c:if>
                                <c:if test="${requestScope.album.type_album.id == 1}">
                                    <strong style="color: #8f4541">Album privé !</strong>
                                </c:if>
                            </div>
                        </div>
                        <br>
                        <c:if test="${requestScope.album.type_album.id == 1}">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <h4 class="card-title">Album partagé avec :</h4>
                                            <table class="table table-bordered table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>
                                                            Nom
                                                        </th>
                                                        <th>
                                                            Prénom
                                                        </th>
                                                        <th>
                                                            Login
                                                        </th>
                                                        <th>
                                                            Actions
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${requestScope.album.utilisateurs}" var="user">
                                                    <tr>
                                                        <td><c:out value="${user.nom}"/></td>
                                                        <td><c:out value="${user.prenom}"/></td>
                                                        <td><c:out value="${user.login}"/></td>
                                                        <td style="text-align: center"><a href="<c:url value="/user/delete-autorisation"/>?id=${requestScope.album.id}&id_user=${user.id}" class="btn btn-danger btn-fw">Retirer</a> </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <br>
                        <div class="row">
                            <div class="col-lg-3">
                                Photos :
                            </div>
                            <div class="col-lg-12">
                                <div class="row row-cols-1 row-cols-md-2">
                                        <c:forEach items="${requestScope.photo}" var="photo">
                                            <div class="col-lg-4">
                                                <div class="card">
                                                    <img class="card-img-top" style="height: 200px" src="<c:url value="/images/${album.titre}/${photo['img_source']}"/>" alt="">
                                                    <div class="card-body">
                                                        <h5 class="card-title"><c:out value="${photo['titre']}" /></h5>
                                                        <p class="card-text"><c:out value="${photo['description']}" /></p>
                                                    </div>
                                                    <div class="card-footer">
                                                        <div class="btn-group" role="group" aria-label="Basic example">
                                                            <a href="<c:url value="/user/edit-photo"/>?id=${photo['id']}&titre=${album.titre}" class="btn btn-primary">Modifier</a>
                                                            <a href="<c:url value="/user/delete-photo"/>?id=${photo['id']}" style="margin-left: 100px" class="btn btn-danger">Supprimer</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                </div>
                            </div>
                        </div>



                    </div>
                </div>
            </div>
            <div class="col-lg-2"></div>
        </div>

    </div>

</body>
</html>
