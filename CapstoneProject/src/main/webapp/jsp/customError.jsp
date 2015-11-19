<%-- 
    Document   : customError
    Created on : Nov 9, 2015, 1:17:23 PM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sarah's Blog</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">
        <link href="${pageContext.request.contextPath}/css/customErrorStyles.css" rel="stylesheet">
        <style>
            
        </style>
    </head>
    <body>
                <div class="container">
                    <h1>Sarah's Blog</h1>
                    <hr/>
                    <div class="navbar">
                            <ul class="nav nav-tabs navbar-right">   
                            <li role="presentation" class="active">
                                <a href="${pageContext.request.contextPath}/home">Home</a>
                            </li>
                        </ul>
                    </div>
                    <div>
                        <h1>An error has occurred...
<!--        <iframe width="100%"
                src="https://www.youtube.com/embed/H6y8fc3n-mI?autoplay=1&controls=0&autohide=1">
        </iframe> -->
                        <img src="http://cdn.omg-facts.com/2014/3/14/45cdf0952d203d05d29dd63a3f555413.jpg"</h1>
                        <h3>${errorMessage}</h3>
                    </div>
                </div>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/admin.js"></script>
    </body>
</html>
