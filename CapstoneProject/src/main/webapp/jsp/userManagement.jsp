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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/userManagementStyles.css" rel="stylesheet">
        <title>JSP Page</title>
        <style>
            @font-face {
                font-family: myFirstFont;
                src: url(${pageContext.request.contextPath}/fonts/Hunt.ttf);
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
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/userManagement">User Management</a>
                            </li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/home">Home</a>
                            </li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/admin">Admin</a>
                            </li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/pinned">Pinned</a>
                            </li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log
                                    Out</a>
                            </li>
                        </ul>
                    </div>

                </div>
                <br>
                <div class="col-md-6" id="creatediv">
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
                                <input type="password" class="form-control" id="confirm-password" placeholder="Password"/>
                            </div>
                        </div>
                        <div class ="form-group">
                            <div class="col-lg-offset-4 col-lg-6 col-md-6 control-label" style="text-align: left">
                                <input type="checkbox" name="active" id="activeAcct" value="false"> Activate New User<br>
                                <input type="checkbox" name="admin" id="adminAcct" value="false"> Admin Privileges<br>
                                <button type="submit" id="createUser" class="btn">Create</button>
                            </div>
                        </div>
                    </form>
                    <div id="validationErrors2" style="color: red"></div>
                </div>
                <div class="col-md-5"style="margin-left: auto;margin-right: auto;">
                    <h2>Users</h2>
                    <table id="itemTable" class="table table-hover" style="border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                        <tr>
                            <th width="20%">User</th>
                            <th width="20%">Status</th>
                            <th width="20%">Edit</th>
                            <th width="20%">Delete</th>
                        </tr>
                        <tbody id="userRows"></tbody>
                    </table>
                </div>

                <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
                     aria-labelledby="editModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span>
                                    <span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="editModalLabel">Edit User Information</h4>
                            </div>
                            <div class="modal-body">
                                <h3 id="contact-id"></h3>
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="add-user-name" class="col-md-4 control-label">
                                            User Name:
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="edit-user-name" placeholder="User Name"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="add-password" class="col-md-4 control-label">
                                            Password:
                                        </label>
                                        <div class="col-md-8">
                                            <input type="password" class="form-control" id="edit-password" placeholder="Password"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="confirm-password" class="col-md-4 control-label">
                                            Confirm Password:
                                        </label>
                                        <div class="col-md-8">
                                            <input type="password" class="form-control" id="confirm-password2" placeholder="Password"/>
                                        </div>
                                    </div>
                                    <div class ="form-group">
                                        <div class="col-lg-offset-4 col-lg-6 col-md-6 control-label" style="text-align: left">
                                            <input type="checkbox" name="active" id="editactiveAcct" value="false"> Activate New User<br>
                                            <input type="checkbox" name="admin" id="editadminAcct" value="false"> Admin Privileges<br>
                                            <button type="submit" id="updateUser" class="btn" data-dismiss="modal">Edit</button>
                                            <button type="button" class="btn" data-dismiss="modal">Cancel</button>
                                        </div>
                                    </div>
                                    <input type="hidden" id="edituserID" value="0">
                                </form>
                                <div id="validationErrors2" style="color:red"/>
                            </div>
                        </div>
                    </div>
                </div>

                <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
                <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                <script src="${pageContext.request.contextPath}/js/admin.js"></script>

                </body>
                </html>

