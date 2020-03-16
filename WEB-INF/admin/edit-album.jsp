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
                        <h5 class="card-title text-center">Formulaire de modification</h5>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form role="form" method="post" action="edit-album">
                                        <div class="alert alert-danger">${ form.erreurs['form'] }</div>
                                        <div class="row">
                                            <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                            <div class="col-xs-5 col-sm-5 col-md-5">
                                                <div class="form-group">
                                                    <label for="theme">Thème de l'album <span class="requis">*</span> :</label>
                                                    <input type="text" name="theme" id="theme" class="form-control input-sm" value="${album.theme}" required><br>
                                                    <div class="alert alert-danger">${ form.erreurs['theme'] }</div>
                                                </div>
                                            </div>
                                        </div>
                                        <input type="hidden" name="id" id="id" value="<c:out value="${album.id}"/>"/>

                                        <div class="row">
                                            <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                            <div class="col-xs-5 col-sm-5 col-md-5">
                                                <div class="form-group">
                                                    <label for="type_album">Type d'album <span class="requis">*</span> :</label>
                                                    <select name="type_album" onchange="getval()" id="type_album" class="form-control input-sm" required>
                                                        <option value="${album.type_album.id}" selected><c:out value="${album.type_album.type_album}"/></option>
                                                        <option value="1">Privé</option>
                                                        <option value="2">Public</option>
                                                    </select>
                                                    <div class="alert alert-danger">${ form.erreurs['type_album'] }</div>
                                                </div>
                                            </div>
                                        </div> <br><br>

                                        <div class="row">
                                            <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                            <div class="col-xs-4 col-sm-4 col-md-4">
                                                <input type="submit" value="Modifier" class="btn btn-info btn-block">
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
