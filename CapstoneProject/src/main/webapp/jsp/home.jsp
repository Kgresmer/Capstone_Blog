<%-- 
    Document   : home
    Created on : Nov 4, 2015, 11:05:45 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <title>Welcome Home</title>
        <link href="${pageContext.request.contextPath}/css/homepage.css" rel="stylesheet"> 
        <style>
            @font-face {
                font-family: myFirstFont;
                src: url(${pageContext.request.contextPath}/fonts/Hunt.ttf);
            }
            @font-face {
                font-family: secondFont;
                src: url(${pageContext.request.contextPath}/fonts/DeadheadRough.ttf);
            }
/*            .profilepic {
                content: url("http://imgur.com/SZ8Cm.jpg");
                height: 45px;
                width: 45px;
            }*/
            .authorlabel {
                display:inline-block;
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
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
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
            <div class="row">
                <div class="col-lg-3 col-md-3 " id="leftcolumn">
                    <div class="row" id="searchbar">
                        <form class="form-horizontal" role="form">
                            <input type="text"
                                   class="form-control"
                                   id="search-text"
                                   placeholder="Search Bar"/>
                            <button type="submit"
                                    id="search-button">
                                Search
                            </button>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="showall-button">Clear Search</a>
                        </form>


                    </div>
                    <div class="row" id="basicinfo">
                        <div class="col-lg-3 col-md-3">
                            <br>
                            <img src="${pageContext.request.contextPath}/img/profilepic.jpg" id="profilepic"/>
                        </div>
                        <div class='col-lg-8 col-md-8 col-lg-offset-1 col-md-offset-1'>
                            <p>Sarah Dutkiewicz</p>
                            <table id="total" class="table ">
                                Total Number of Posts:

                                <tbody id="totalPosts"></tbody>
                            </table>

                        </div>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Established November 4, 2015
                    </div>
                    <div class="row" id="facebookinfo">
                        Follow Us on Facebook<br>
                        @<a href="https://www.facebook.com/profile.php?id=100010586469202">SadukieBlog</a>
                    </div>
                    <div class="row" id="tagsinfo">
                        Tags &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="showall-button">Clear Tag Search</a><br>
                        <div id="taglist"></div><br>

                    </div>
                </div>
                <div class="col-lg-8 col-md-8" id="postscolumn">
                    <div id="total1">
                        <table  class="table ">
                            <h2>Pinned Posts</h2>
                            <tbody id="postPinRows"></tbody>
                        </table>
                    </div>
                    <div id="total2">
                        <table  class="table ">
                            <h2>Recent Activity</h2>
                            <tbody id="postRows"></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row" id="bottombar">
                <div class="navbar ">
                    <ul class="nav nav-tabs">   
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/employee">Login</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/disclaimer">Disclaimer</a>
                        </li>
                    </ul>
                </div>
            </div>

        </div>




    </div>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> 
    <script src="${pageContext.request.contextPath}/js/admin.js"></script>
</body>
</html>
