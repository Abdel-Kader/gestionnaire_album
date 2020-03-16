<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 21/02/2020
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Connexion</title><head>
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
</head>

<body>
<%--<% String form = (String) request.getAttribute("form"); %>--%>
    <div class="container-scroller">
        <div class="container-fluid page-body-wrapper full-page-wrapper">
            <div class="content-wrapper d-flex align-items-center auth auth-bg-1 theme-one">
                <div class="row w-100">
                    <div class="col-lg-5 mx-auto">
                        <div class="auto-form-wrapper">
                            <strong style="color: #85332e;text-align: center">${ form.erreurs['form'] }</strong><br><br>
                            <form action="login" method="post" role="form">
                                <div class="form-group">
                                    <label class="label" for="login">Login</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="login" name="login" placeholder="Username"/>
                                        <div class="input-group-append">
                                            <span class="input-group-text">
                                              <i class="mdi mdi-account"></i>
                                            </span>
                                        </div>
                                    </div>
                                    <strong style="color:#85332e;width: 100%">${ form.erreurs['login'] }</strong>
                                </div>
                                <div class="form-group">
                                    <label class="label" for="password">Mot de passe</label>
                                    <div class="input-group">
                                        <input type="password" name="password" id="password" class="form-control" placeholder="*********"/>
                                        <div class="input-group-append">
                                            <span class="input-group-text">
                                              <i class="mdi mdi-security"></i>
                                            </span>
                                        </div>
                                    </div>
                                    <strong style="color:#85332e;width: 100%">${ form.erreurs['password'] }</strong>
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-primary submit-btn btn-block">Login</button>
                                </div>

                                <div class="text-block text-center my-3">
                                    <span class="text-small font-weight-semibold">Vous n'avez pas encore de compte ?</span>
                                    <a href="register" class="text-black text-small">Créer votre compte</a>
                                </div>
                            </form>
                        </div>
                        <ul class="auth-footer">
                            <li>
                                <a href="#">Conditions</a>
                            </li>
                            <li>
                                <a href="#">Help</a>
                            </li>
                            <li>
                                <a href="#">Terms</a>
                            </li>
                        </ul>
                        <p class="footer-text text-center">copyright © 2018 Bootstrapdash. All rights reserved.</p>
                    </div>
                </div>
            </div>
            <!-- content-wrapper ends -->
        </div>
        <!-- page-body-wrapper ends -->
    </div>
</body>
</html>
