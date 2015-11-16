<%-- 
    Document   : createNewUser
    Created on : Nov 16, 2015, 9:25:03 AM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:out value="${pageContext.request.userPrincipal.name}" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <title>JSP Page</title>
        <style>
            @font-face {
                font-family: myFirstFont;
                src: url(${pageContext.request.contextPath}/fonts/Hunt.ttf);
            }

            body {
                font-size: 14px;
                background-image: url('css/wall.jpg');
                background-color: black;
                color: black;
            }
            .container {
                background-color: white;
            }
            input {
                line-height: 2.3em; 
            }
            label {
                line-height: 2.3em; 
            }
        </style>
    </head>
    <body>

        <div class="container" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
            <br>
            <div class = "row" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                <div class="navbar navbar-default">
                    <div class="navbar-header">
                        <ul class="nav navbar-nav">
                            <li role="presentation" class="active">
                                <a href="${pageContext.request.contextPath}/createUser">Create User</a>
                            </li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/home">Home</a>
                            </li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/admin">Admin</a>
                            </li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log
                                    Out</a>
                            </li>
                        </ul>
                    </div>

                </div>
                <br>
                <div class="col-md-6 ">
                    <h2 class ="col-md-offset-4">Create New User</h2><br>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-user-name" class="col-md-4 control-label">
                                User Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-user-name" placeholder="User Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-password" class="col-md-4 control-label">
                                Password:
                            </label>
                            <div class="col-md-8">
                                <input type="password" class="form-control" id="add-password" placeholder="Password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirm-password" class="col-md-4 control-label">
                                Confirm Password:
                            </label>
                            <div class="col-md-8">
                                <input type="confirm password" class="form-control" id="corfirm-password" placeholder="Password"/>
                            </div>
                        </div>
                        <div class ="form-group">
                            <div class="col-md-6 control-label">
                                <input type="checkbox" name="active" id="activeAcct" value="false"> Activate New User<br>
                                <input type="checkbox" name="admin" id="adminAcct" value="false"> Admin Privileges<br>
                                <button type="submit" id="createUser" class="btn">Create</button>
                            </div>
                        </div>
                    </form>
                </div>

                <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
                <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                <script src="${pageContext.request.contextPath}/js/admin.js"></script>
                <!--                        <script type="text/javascript">
                                            tinymce.init({
                                                selector: "textarea",
                                                toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
                                            });
                                        </script>-->

                </body>
                </html>

