<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 09/03/2020
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Gestion des albums</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="<c:url value='/css/assets/vendors/iconfonts/font-awesome/css/font-awesome.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/assets/vendors/iconfonts/mdi/css/materialdesignicons.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/assets/vendors/iconfonts/ionicons/css/ionicons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/assets/vendors/iconfonts/typicons/src/font/typicons.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/assets/vendors/iconfonts/flag-icon-css/css/flag-icon.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/assets/vendors/css/vendor.bundle.base.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/assets/vendors/css/vendor.bundle.addons.css'/>">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="<c:url value='/css/assets/css/shared/style.css'/>">
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="<c:url value='/css/assets/css/demo_1/style.css'/>">
    <!-- End Layout styles -->
</head>
<body>
<div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar default-layout col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
        <div class="text-center navbar-brand-wrapper d-flex align-items-top justify-content-center">
            <a class="navbar-brand" href="<c:url value="/admin"/>">Album Photo</a>
        </div>
        <div class="navbar-menu-wrapper d-flex align-items-center">

            <ul class="navbar-nav ml-auto">

                <li class="nav-item dropdown d-none d-xl-inline-block user-dropdown">
                    <a class="nav-link dropdown-toggle" id="UserDropdown" href="#" data-toggle="dropdown" aria-expanded="false">
                        <img class="img-xs rounded-circle" src="<c:url value="/images/user.jpg"/>" alt="Profile image"> </a>
                    <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="UserDropdown">
                        <div class="dropdown-header text-center">
                            <p class="mb-1 mt-3 font-weight-semibold"><c:out value="${sessionScope.utilisateur.login}"/></p>
                        </div>
                        <a class="dropdown-item">My Profile <span class="badge badge-pill badge-danger">1</span><i class="dropdown-item-icon ti-dashboard"></i></a>
                        <a class="dropdown-item" href="<c:url value="/deconnexion"/> ">Déconnexion<i class="dropdown-item-icon ti-power-off"></i></a>
                    </div>
                </li>
            </ul>
            <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
                <span class="mdi mdi-menu"></span>
            </button>
        </div>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
        <!-- partial:partials/_sidebar.html -->
        <nav class="sidebar sidebar-offcanvas" id="sidebar">
            <ul class="nav">

                <li class="nav-item nav-category">Main Menu</li>

                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" href="#albums" aria-expanded="false" aria-controls="ui-basic">
                        <i class="menu-icon typcn typcn-coffee"></i>
                        <span class="menu-title">Gestion des albums</span>
                        <i class="menu-arrow"></i>
                    </a>
                    <div class="collapse" id="albums">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/admin/list-album"/> ">Lister</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value='/user/add-album'/>">Ajouter</a>
                            </li>
                        </ul>
                    </div>
                </li>

                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" href="#users" aria-expanded="false" aria-controls="auth">
                        <i class="menu-icon typcn typcn-document-add"></i>
                        <span class="menu-title">Gestion des utilisateurs</span>
                        <i class="menu-arrow"></i>
                    </a>
                    <div class="collapse" id="users">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value='/admin/list-user'/>?req=all">Lister</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value='/admin/add-user'/>">Ajouter</a>
                            </li>

                        </ul>
                    </div>
                </li>
            </ul>
        </nav>
        <!-- partial -->

