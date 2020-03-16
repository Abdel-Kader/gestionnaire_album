<%@ page import="java.util.List" %>
<%@ page import="entities.Album" %><%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 03/03/2020
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<% List<Album> album = (List<Album>) request.getAttribute("album"); %>--%>
<html>
<head>
    <title>Ajouter une photo</title>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="container" style="margin-top: 50px">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12 mx-auto">
            <div class="card card-signin">
                <div class="card-body">
                    <h5 class="card-title text-center">Formulaire d'ajout de photo</h5>
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form role="form" method="post" action="<c:url value="/user/add-photo"/>" enctype="multipart/form-data">
                                    <strong style="color: #85332e;text-align: center">${ form.erreurs['form'] }</strong><br><br>
                                    <div class="row">
                                        <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                        <div class="col-xs-5 col-sm-5 col-md-5">
                                            <div class="form-group">
                                                <label for="titre">Titre de la photo <span class="requis">*</span> :</label>
                                                <input type="text" name="titre" id="titre" class="form-control input-sm" placeholder="titre" required><br>
                                                <strong style="color: #85332e;text-align: center">${ form.erreurs['titre'] }</strong>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                        <div class="col-xs-5 col-sm-5 col-md-5">
                                            <div class="form-group">
                                                <label for="description">Description de l'image <span class="requis">*</span> :</label>
                                                <textarea name="description" id="description" class="form-control input-sm"></textarea>
                                                <strong style="color: #85332e;text-align: center">${ form.erreurs['description'] }</strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                        <div class="col-xs-5 col-sm-5 col-md-5">
                                            <div class="form-group">
                                                <label for="album">Dans quel album mettre l'image ? <span class="requis">*</span> :</label>
                                                <select name="album" id="album" class="form-control input-sm" required>
                                                    <c:forEach items="${requestScope.album}" var="album">
                                                        <option value="${album['id']}"><c:out value="${album['theme']}" /></option>
                                                    </c:forEach>
                                                </select>
                                                <strong style="color: #85332e;text-align: center">${ form.erreurs['type_album'] }</strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                        <div class="col-xs-5 col-sm-5 col-md-5">
                                            <div class="form-group">
                                                <label for="img_src">Joindre l'image <span class="requis">*</span> :</label>
                                                <input type="file" name="img_src" id="img_src" class="form-control input-sm" required><br>
                                                <strong style="color: #85332e;text-align: center">${ form.erreurs['img_src'] }</strong>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                        <div class="col-xs-5 col-sm-5 col-md-5">
                                            <label for="key_words">Mots clés (Tapez sur entrée pour ajouter):</label>
                                            <input id="key_words" class="form-control" name="key_words" type="text" value=""/>
                                        </div>
                                    </div><br><br>
                                    <div class="row">
                                        <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                        <div class="col-xs-4 col-sm-4 col-md-4">
                                            <input type="submit" value="Ajouter" class="btn btn-info btn-block">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>