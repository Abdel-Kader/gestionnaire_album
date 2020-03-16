<%--
  Created by IntelliJ IDEA.
  User: Abdel-Kader
  Date: 09/03/2020
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="header.jsp"/>
<div class="main-panel">
    <div class="content-wrapper">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-3 grid-margin stretch-card">
                        <div class="card" id="card" style="background-color: #ff5e00">
                            <div class="card-body pb-0">
                                <div class="d-flex justify-content-between">
                                    <h4 class="card-title mb-0"><a href="<c:url value="/admin/list-user"/>?req=user">Total utilisateurs</a></h4>
                                </div>
                                <h3 class="font-weight-medium mb-4">${nb_user}</h3>
                            </div>
                            <canvas class="mt-n4" height="90" id="total-revenue"></canvas>
                        </div>
                    </div>
                    <div class="col-md-3 grid-margin stretch-card">
                        <div class="card" id="card">
                            <div class="card-body pb-0">
                                <div class="d-flex justify-content-between">
                                    <h4 class="card-title mb-0"><a href="<c:url value="/admin/list-user"/>?req=admin">Total administrateurs</a></h4>
                                </div>
                                <h3 class="font-weight-medium mb-4">${nb_admin}</h3>
                            </div>
                            <canvas class="mt-n4" height="90" id="total-admin"></canvas>
                        </div>
                    </div>
                    <div class="col-md-3 grid-margin stretch-card">
                        <div class="card" id="card" style="background-color: #fed136">
                            <div class="card-body pb-0">
                                <div class="d-flex justify-content-between">
                                    <a href="<c:url value="/admin/list-album"/> "><h4 class="card-title mb-0">Total albums</h4></a>
                                </div>
                                <h3 class="font-weight-medium">${nb_album}</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
