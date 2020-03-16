<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 21/02/2020
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/assets/vendors/iconfonts/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/assets/vendors/iconfonts/mdi/css/materialdesignicons.min.css'/>"/>
<link rel="stylesheet" href="<c:url value='/css/assets/vendors/iconfonts/ionicons/css/ionicons.css'/>"/>
<link rel="stylesheet" href="<c:url value='/css/assets/vendors/iconfonts/typicons/src/font/typicons.css'/>">
<link rel="stylesheet" href="<c:url value='/css/assets/vendors/iconfonts/flag-icon-css/css/flag-icon.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/assets/vendors/css/vendor.bundle.base.css'/>">
<link rel="stylesheet" href="<c:url value='/css/assets/vendors/css/vendor.bundle.addons.css'/>">
<link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/assets/css/shared/style.css'/>">
<link rel="stylesheet" href="<c:url value='/css/swal.css'/>">
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet" href="<c:url value='/css/assets/css/demo_1/style.css'/>">
<script src="<c:url value='/js/jquery.js'/>"></script>
<script src="<c:url value='/js/poper.js'/>"></script>
<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/script.js'/>"></script>
<script src="<c:url value='/js/swal.js'/>"></script>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark" id="banner">
    <div class="container">
        <!-- Brand -->
        <a class="navbar-brand" href="<c:url value='/home'/>"><span>Album</span> Photo</a>

        <!-- Toggler/collapsibe Button -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar links -->
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <c:if test="${!empty sessionScope.utilisateur}">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/user/mes-album'/>">Mes albums</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/user/add-album'/>">Ajouter un album</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/user/add-photo'/>">Ajouter une photo à un album</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/user/with-me'/>">Partagés avec moi</a>
                    </li>

                </ul>
            </c:if>
        </div>
    </div>

    <c:if test="${empty sessionScope.utilisateur}">
        <a href="login" id="btn-connect" class="btn btn-outline-success my-2 my-sm-0">Se connecter</a>
    </c:if>

    <!-- Dropdown -->
    <c:if test="${!empty sessionScope.utilisateur}">
<%--        <div class="dropdown show">--%>
<%--            <a class="btn btn-outline-success my-2 my-sm-0 dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--                <span id="profil">Bienvenue ${sessionScope.utilisateur.login}</span>--%>
<%--            </a>--%>

<%--            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">--%>
<%--                <a class="dropdown-item" href="#">Mon Profil</a>--%>
<%--                <a class="dropdown-item" href="#">Another action</a>--%>
<%--                <a class="dropdown-item" href="#">Déconnexion</a>--%>
<%--            </div>--%>
<%--        </div>--%>

        <li class="nav-item dropdown d-none d-xl-inline-block user-dropdown">
            <a class="nav-link dropdown-toggle" id="UserDropdown" href="#" data-toggle="dropdown" aria-expanded="false">
                <img class="img-xs rounded-circle" src="<c:url value="/images/user.jpg"/>" alt="Profile image"> </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="UserDropdown">
                <div class="dropdown-header text-center">
                    <p class="mb-1 mt-3 font-weight-semibold"><c:out value="${sessionScope.utilisateur.login}"/></p>
                </div>
                <a class="dropdown-item">Mon profil<span class="badge badge-pill badge-danger">1</span><i class="dropdown-item-icon ti-dashboard"></i></a>
                <a class="dropdown-item" href="<c:url value="/deconnexion"/> ">Déconnexion<i class="dropdown-item-icon ti-power-off"></i></a>
            </div>
        </li>
    </c:if>
</nav>
