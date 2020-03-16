<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 21/02/2020
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Album </title>
<body>
<c:import url="../header.jsp"/>
<div class="container-fluid">
    <div class="presentation">
        <h3>Album <c:out value="${album.theme}"/></h3>
        <h5>Ajouté le <c:out value="${album.created_at.toLocaleString()}"/> par <b><c:out value="${album.proprietaire.login}"/></b></h5>
    </div><br>
    <div class="row text-center text-lg-left">

        <c:forEach items="${requestScope.photo}" var="photo">
            <div class="col-lg-3 col-md-3">
                <img class="im img-fluid" id="${photo.id}" onclick="detail('${photo.description}','${photo.id}','${photo.titre}');" src="<c:url value="/images/${album.titre}/${photo['img_source']}"/>" alt="${photo.description}" style="width:100%;height: 100%">
<%--                    <img id="myImg" src="<c:url value="/images/${album.titre}/${photo['img_source']}"/>" alt="Snow" style="width:100%;max-width:300px">--%>
<%--                    <img class="img-fluid img-thumbnail" src="<c:url value="/images/${album.titre}/${photo['img_source']}"/>" alt="">--%>
<%--                    <div class="overlay">--%>
<%--                        <div class="titre_photo">--%>
<%--                            <c:out value="${photo['titre']}" />--%>
<%--                        </div>--%>
<%--                    </div>--%>
            </div>
        </c:forEach>
    </div><br>


    <div id="myModal" class="modal">
        <span class="close">&times;</span>
        <div id="head"></div>
        <img class="modal-content" id="img01">
        <div id="caption"></div>
    </div>
</div>
<script>
    // Get the modal
    let modal = document.getElementById("myModal");
function detail(alt,id,titre)
{
    let src = document.getElementById(id).getAttribute("src");
    var modalImg = document.getElementById("img01");
    var captionText = document.getElementById("caption");
    var head = document.getElementById("head");
        modal.style.display = "block";
        modalImg.src = src;
        captionText.innerHTML = alt;
        head.innerHTML = titre;
}

    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }
</script>
</body>
</html>
