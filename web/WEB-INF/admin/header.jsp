<%-- 
    Document   : header
    Created on : Nov 25, 2019, 10:48:38 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>Hamro Tarkari Bazzar</title>
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="Mosaddek" name="author" />
   <link href="assets/admin/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />   
   <link href="assets/admin/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
   <link href="assets/admin/plugins/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
   <link href="assets/admin/plugins/font-awesome/css/font-awesome.css" type="text/css" rel="stylesheet" />
   
   <link href="assets/admin/css/style.css" rel="stylesheet" />
   <link href="assets/admin/css/style-responsive.css" rel="stylesheet" />
   <link href="assets/admin/css/style-default.css" rel="stylesheet" id="style_color" />
   <link href="assets/admin/plugins/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
   <link href="assets/admin/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
  <link href="assets/admin/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
  <link href="assets/admin/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
  <link href="assets/admin/plugins/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
  <link href="assets/admin/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="assets/admin/plugins/uniform/css/uniform.default.css" />
  <link rel="stylesheet" type="text/css" href="assets/admin/css/profimg.css" />
  <script src="assets/admin/plugins/jquery/jquery.min.js"></script>
  <script type="text/javascript" src="assets/admin/js/jquery-1.8.3.min.js"></script>
   
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
   <!-- BEGIN HEADER -->
   <div id="header" class="navbar navbar-inverse navbar-fixed-top">
       <!-- BEGIN TOP NAVIGATION BAR -->
       <div class="navbar-inner">
           <div class="container-fluid">              
               <!-- BEGIN LOGO -->
               <a class="brand" href="dashboard" style="width: 100px; margin-top: 0px; " >
                   <img src="assets/admin/images/present.jpg" alt="HamroTarkariBazzar" />
               </a>
               <!-- END LOGO -->
               <div class="top-nav ">
                   <ul class="nav pull-right top-menu" >          
                       <!-- BEGIN USER LOGIN DROPDOWN -->
                       <c:set var="uname" scope="page" value="${sessionScope.loggedInUser}" />
                           <c:if test="${uname != null}"> 
                       <li class="dropdown">
                           <a href="#" class="dropdown-toggle" data-toggle="dropdown">                                                            
                         
                               <img src="" width="30px" height="30px">
                                
                                <span class="username" style="color: black;font-size: 15px;text-transform: capitalize">${fn:toLowerCase(uname.fname)} ${fn:toLowerCase(uname.lname)}</span> 
                               
                           </a>
                           <ul class="dropdown-menu extended logout">                              
                               <li><a href=""><i class="icon-key"></i> Log Out</a></li>
                           </ul>
                       </li>
                           </c:if>
                       <!-- END USER LOGIN DROPDOWN -->
                   </ul>
                   <!-- END TOP NAVIGATION MENU -->
               </div>
           </div>
       </div>
       <!-- END TOP NAVIGATION BAR -->
   </div>
   <!-- END HEADER -->
   <!-- BEGIN CONTAINER -->
   <div id="container" class="row-fluid">