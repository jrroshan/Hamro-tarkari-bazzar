<%-- 
    Document   : login
    Created on : Nov 13, 2019, 8:35:18 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Title Page-->
    <title>Hamro Tarkari Bazzar</title>

    <!-- Icons font CSS-->
    <link href="assets/login/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="assets/login/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="assets/login/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="assets/login/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link rel="stylesheet" href="assets/login/css/bootstrap.min.css">
    
    <link href="assets/login/css/main.css" rel="stylesheet" media="all">
</head>

<body>
    <div class="page-wrapper bg-red p-t-180 p-b-100 font-robo">
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                <div class="card-heading"></div>
                <div class="card-body">
                    <h2 class="title">Login Form</h2>
                    <div style="padding-left: 12px;">
                    <c:set var="errmsg" scope="page" value="${errorMsg}"></c:set>
                    <c:if test="${errmsg != null}">
                        <div class="btn btn-danger">
                            <span style="font-size: 15px;">${errmsg}</span>
                        </div>
                    </c:if>
                    </div>
                    <form action="dashboard" method="POST">
                        <div class="">
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input class="input--style-2" type="text" name="username" placeholder="Username" >
                                </div>
                            </div>
                        </div>
                        <div class="">
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input class="input--style-2" type="password" name="password" placeholder="Password" >
                                </div>
                            </div>
                        </div>
                        <div class="p-t-30">
                            <button class="btn btn--radius btn--green" type="submit">Login</button>                        
                        </div>
                        <div class="p-t-30">
                            <div class="">
                                <p>Don't have account?? <a class="btn btn--radius btn--green" href="signup" role="button">Sign Up</a></p><p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Jquery JS-->
    <script src="assets/login/vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="assets/login/vendor/select2/select2.min.js"></script>
    <script src="assets/login/vendor/datepicker/moment.min.js"></script>
    <script src="assets/login/vendor/datepicker/daterangepicker.js"></script>

    <!-- Main JS-->
    <script src="assets/login/js/global.js"></script>

</body>

</html>
<!-- end document-->