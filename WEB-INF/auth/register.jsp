<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 21/02/2020
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/main.css'/>" rel="stylesheet">
<html>
<head>
    <title>Inscription</title>
</head>

<body>
<%--<% String form = (String) request.getAttribute("form"); %>--%>
<div class="container" style="margin-top: 50px">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12 mx-auto">
            <div class="card card-signin">
                <div class="card-body">
                    <h5 class="card-title text-center">Formulaire d'inscription</h5>
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form role="form" method="post" action="register">
                                    <div class="row">
                                        <input type="hidden" name="req" value="user"/>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <label for="nom">Votre nom <span class="requis">*</span> :</label>
                                                <input type="text" name="nom" id="nom" class="form-control input-sm" placeholder="nom" required/><br>
                                                <div class="alert alert-danger">${ form.erreurs['nom'] }</div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <label for="prenom">Votre prénom <span class="requis">*</span> :</label>
                                                <input type="text" name="prenom" id="prenom" class="form-control input-sm" placeholder="prénom" required><br>
                                                <div class="alert alert-danger">${ form.erreurs['prenom'] }</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <label for="login">Votre login <span class="requis">*</span> :</label>
                                                <input type="text" name="login" id="login" class="form-control input-sm" placeholder="Votre login" required><br>
                                                <div class="alert alert-danger">${ form.erreurs['login'] }</div>
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <label for="telephone">Votre téléphone :</label>
                                                <input type="number" pattern="/^-?\d+\.?\d*$/" onKeyPress="if(this.value.length===9) return false;" name="telephone" id="telephone" class="form-control input-sm" minlength="9" maxlength="9" placeholder="Ex: 77*******"><br>
                                                <div class="alert alert-danger">${ form.erreurs['telephone'] }</div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <label for="password">Votre mot de passe <span class="requis">*</span> :</label>
                                                <input type="password" name="password" id="password" class="form-control input-sm" placeholder="mot de passe" required><br>
                                                <div class="alert alert-danger">${ form.erreurs['password'] }</div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <label for="password_confirmation">Confirmer votre mot de passe <span class="requis">*</span> :</label>
                                                <input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-sm" placeholder="Confirmer le mot de passe" required><br>
                                                <div class="alert alert-danger">${ form.erreurs['password_confirmation'] }</div>
                                            </div>
                                        </div>
                                    </div> <br><br>

                                    <div class="row">
                                        <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                        <div class="col-xs-4 col-sm-4 col-md-4">
                                            <input type="submit" value="S'inscrire" class="btn btn-info btn-block">
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
