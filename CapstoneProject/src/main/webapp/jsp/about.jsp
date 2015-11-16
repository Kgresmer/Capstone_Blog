<%-- 
    Document   : about us
    Created on : Nov 4, 2015, 11:51:49 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Us</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/aboutStyles.css" rel="stylesheet">
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/contact">Contact</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/about">About Us</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/disclaimer">Disclaimer</a>
                    </li>
                </ul>
            </div>
                    <hr>
            <h1>About Us</h1>
            <div class ="col-md-offset-1">Sarah <br>
                Although she's primarily focused on training and mentoring .NET and Java apprentices  at the Software Craftsmanship Guild in Akron, OH, Sarah Dutkiewicz is known for troubleshooting problems in various Microsoft-based realms - including .NET applications, SQL Server configuration and administration, and general IT administration via PowerShell.  From tech support to IT to software development, Sarah can apply her love for technology in various arenas.  You can find Sarah primarily attending, speaking at, and organizing various user groups and developer events throughout the Heartland (OH/MI/KY/TN) District.  Sarah is the owner of Cleveland Tech Consulting, LLC.    She maintains a technical blog at http://www.codinggeekette.com and runs a technology-agnostic community site for the Cleveland, OH area at http://www.clevelandtechevents.com.  She also is a coauthor on "Automating Microsoft Windows Server 2008 R2 Administration with Windows PowerShell 2.0", which was released May 2011.</div>
            <br>
            <br>
            <br>
            <br>
            <div>Creators</div>
            <p class ="col-md-offset-1">Alex Andrzejek</p>
            <br>
            <p class ="col-md-offset-1">Kevin Gresmer</p>
            <br>
            <p class ="col-md-offset-1">Shawn Herdina</p>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
