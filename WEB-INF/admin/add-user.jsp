<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 09/03/2020
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="header.jsp"/>
<div class="main-panel">
    <div class="content-wrapper">
        <div class="row">
            <div class="col-sm-12 col-md-12 col-lg-12 mx-auto">
                <div class="card card-signin">
                    <div class="card-body">
                        <h5 class="card-title text-center">Formulaire d'ajout utilisateur</h5>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form role="form" method="post" action="add-user">
                                        <div class="row">
                                            <input type="hidden" name="req" value="admin"/>
                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group">
                                                    <label for="nom">Nom de l'utilisateur<span class="requis">*</span> :</label>
                                                    <input type="text" name="nom" id="nom" class="form-control input-sm" placeholder="nom" required/><br>
                                                    <div class="alert alert-danger">${ form.erreurs['nom'] }</div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group">
                                                    <label for="prenom">Prenom de l'utilisateur <span class="requis">*</span> :</label>
                                                    <input type="text" name="prenom" id="prenom" class="form-control input-sm" placeholder="prénom" required><br>
                                                    <div class="alert alert-danger">${ form.erreurs['prenom'] }</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group">
                                                    <label for="login">Login de l'utilisateur<span class="requis">*</span> :</label>
                                                    <input type="text" name="login" id="login" class="form-control input-sm" placeholder="login" required><br>
                                                    <div class="alert alert-danger">${ form.erreurs['login'] }</div>
                                                </div>
                                            </div>

                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group">
                                                    <label for="telephone">Téléphone de l'utilisateur :</label>
                                                    <input type="tel" name="telephone" id="telephone" class="form-control input-sm" placeholder="Téléphone"><br>
                                                    <div class="alert alert-danger">${ form.erreurs['telephone'] }</div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                            <div class="col-xs-5 col-sm-5 col-md-5">
                                                <div class="form-group">
                                                    <label for="type_user">Type d'utilisateur <span class="requis">*</span> :</label>
                                                    <select name="type_user" id="type_user" class="form-control input-sm" required>
                                                        <option value="">-------------------------------</option>
                                                        <option value="1">Administrateur</option>
                                                        <option value="2">Utilisateur</option>
                                                    </select>
                                                    <div class="alert alert-danger">${ form.erreurs['type_user'] }</div>
                                                </div>
                                            </div>
                                        </div>
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
</div>
