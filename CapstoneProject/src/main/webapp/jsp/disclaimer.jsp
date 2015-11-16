<%-- 
    Document   : disclaimer
    Created on : Nov 4, 2015, 11:51:33 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disclaimer</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/disclaimerStyles.css" rel="stylesheet">
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/contact">Contact</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/about">About Us</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/disclaimer">Disclaimer</a>
                    </li>
                </ul>
            </div>
                    <hr>
            <h1>Disclaimer</h1>
            <br>
            <br>
            <p class ="col-md-offset-1">These are my own personal thoughts, beliefs, and perspectives of the world. 
                This is not representative of any employers, planning committees, clients, or any other
                associations tied with me or my name. Every person has an opinion on something, and these
                are mine.  We may end up having to agree to disagree, and I can guarantee you that not 
                everyone will feel the way that I do.
            </p>
            <br>
            <br>
            <p class ="col-md-offset-1">I may post happy things or sad things, run-of-the-mill things or controversial things. 
                Whatever I post is most likely posted straight out of my mind, as I write stream-of-conscious
                style.
            </p>
            <br>
            <br>
            <p class ="col-md-offset-1">If you have any questions for me personally, please feel free to email me at sadukiecapstone@gmail.com.
            </p>
            <br>
            <br>
            <p class ="col-md-offset-1">- Sarah "sadukie" Dutkiewicz</p>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
