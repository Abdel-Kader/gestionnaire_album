<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 02/03/2020
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un album</title>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2 col-md-2 col-lg-2 mx-auto"></div>
        <div class="col-sm-8 col-md-8 col-lg-8 mx-auto">
            <div class="card" style="background-color: #ccc">
                <div class="card-body">
                    <h5 class="card-title text-center">Formulaire d'ajout album</h5>
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form role="form" method="post" action="<c:url value="/user/add-album"/> ">
                                    <strong style="color: #85332e;text-align: center">${ form.erreurs['form'] }</strong><br><br>
                                    <div class="row">
                                        <div class="col-xs-3 col-sm-3 col-md-3"></div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <label for="theme">Thème de l'album <span class="requis">*</span> :</label>
                                                <input type="text" name="theme" id="theme" class="form-control input-sm" placeholder="Ex: vacances, voyages" required><br>
                                                <strong style="color: #85332e;text-align: center">${ form.erreurs['theme'] }</strong>
                                            </div>
                                        </div>
                                    </div>
                                    <br><br>
                                    <div class="row">
                                        <div class="col-xs-3 col-sm-3 col-md-3"></div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <label for="type_album">Type d'album <span class="requis">*</span> :</label>
                                                <select name="type_album" onchange="getval()" id="type_album" class="form-control input-sm" required>
                                                    <option value="">-------------------------------</option>
                                                    <option value="1">Privé</option>
                                                    <option value="2">Public</option>
                                                </select>
                                                <strong style="color: #85332e;text-align: center">${ form.erreurs['type_album'] }</strong>
                                            </div>
                                        </div>
                                    </div> <br><br>
                                    <div class="row">
                                        <div class="col-xs-4 col-sm-4 col-md-4"></div>
                                        <div class="col-xs-5 col-sm-5 col-md-5"  style="display: none" id="user">
                                            <div class="form-group">
                                                <label for="type_album">Les utilisateurs auttorisés à voir votre album : </label><br><br>
                                                    <c:forEach items="${requestScope.users}" var="users">
                                                        <div class="form-check">
                                                            <input class="form-check-input" value="<c:out value="${users['id']}"/>" type="checkbox" name="user" id="defaultCheck1"/>
                                                            <label class="form-check-label" for="defaultCheck1">
                                                                <c:out value="${users['login']}"/>
                                                            </label>
                                                        </div>
                                                    </c:forEach>
                                            </div>
                                        </div>
                                    </div>
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
        <div class="col-sm-2 col-md-2 col-lg-2 mx-auto"></div>
    </div>
</div>

</body>
</html>
<script>
function getval()
{
   let x= document.getElementById("type_album").value;
    if(x === '1'){
        document.getElementById("user").style.display = "block";
    }
    else{
        document.getElementById("user").style.display = "none";
    }
}
</script>