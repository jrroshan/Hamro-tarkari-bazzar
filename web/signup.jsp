<%-- 
    Document   : signup
    Created on : Nov 21, 2019, 9:54:07 PM
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
    <link href="assets/login/css/main.css" rel="stylesheet" media="all">
</head>

<body>
    <div class="page-wrapper bg-red p-t-180 p-b-100 font-robo">
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                <div class="card-heading"></div>
                <div class="card-body">
                    <h2 class="title">Registration Info</h2>
                    <c:set var="errmsg" scope="page" value="${errorMsg}"></c:set>
                    <c:if test="${errmsg != null}">
                        <div>
                            <span style="font-size: 15px;">${errmsg}</span>
                        </div>
                    </c:if>
                    <form method="POST" action="register" enctype="multipart/form-data">
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-2" type="text" placeholder="First Name" name="fname">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                     <input class="input--style-2" type="text" placeholder="Last Name" name="lname">
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-2" type="text" placeholder="Address" name="address">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-2" type="text" placeholder="Username" name="username">
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-2" type="password" placeholder="Password" name="password">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <select name="gender">
                                            <option disabled="disabled" selected="selected">Gender</option>
                                            <option value="male">Male</option>
                                            <option value="female">Female</option>
                                        </select>
                                        <div class="select-dropdown"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-2" type="text" placeholder="City" name="city">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                   <input class="input--style-2" type="text" placeholder="State" name="state">
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-2" type="text" placeholder="Country" name="country">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <input class="input--style-2" type="text" placeholder="Phone Number" name="phone">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-2" type="file"  name="file" />
                                </div>
                            </div>                            
                        </div>

                        <!-- <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-2" type="text" placeholder="Registration Code" name="res_code">
                                </div>
                            </div>
                        </div> -->
                        <div class="p-t-30">
                            <button class="btn btn--radius btn--green" type="submit">Submit</button>
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