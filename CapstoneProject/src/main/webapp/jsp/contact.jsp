<%-- 
    Document   : contact
    Created on : Nov 4, 2015, 11:52:13 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Us</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/contactStyles.css" rel="stylesheet">
        <style>
            @font-face {
                font-family: myFirstFont;
                src: url(${pageContext.request.contextPath}/fonts/Hunt.ttf);
            }
            @font-face {
                font-family: secondFont;
                src: url(${pageContext.request.contextPath}/fonts/DeadheadRough.ttf);
            }

        </style>  
    </head>
    <body>
        
        <div class="container-fluid">
            <div class="navbar">
                <div class="navbar-header" id='headerTitle'>
                    <a class="navbar-brand" href="#" >Sarah's Blog</a>
                </div>
                
                <ul class="nav nav-tabs navbar-right">   
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/contact">Contact</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/about">About Us</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/disclaimer">Disclaimer</a>
                    </li>
                </ul>
            </div>
                    <hr>
            <h1>Contact Us</h1>
            <br>
            <br>
            <p class ="col-md-offset-1">Sarah "sadukie" Dutkiewicz
                <br>
                Follow me on Facebook<br>
                <a href="https://www.facebook.com/profile.php?id=100010586469202"><img src="${pageContext.request.contextPath}/img/likeusonfacebook.jpg" height="60"/>
                </a>
                <br>
                @<a href="https://www.facebook.com/profile.php?id=100010586469202">SadukieBlog</a>
                <br>
                Email me at sadukiecapstone@gmail.com
            </p>
            <br>
            <br>
            <br>
            <h3>Creators</h3>
            <p class ="col-md-offset-1">Alex - alex.andrzejek@gmail.com</p>
            <p class ="col-md-offset-1">Kevin - kgresmer@gmail.com</p>
            <p class ="col-md-offset-1">Shawn - sherdina@gmail.com</p>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        </div>
    </body>
</html>
