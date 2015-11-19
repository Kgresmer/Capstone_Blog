<%-- 
    Document   : user
    Created on : Nov 4, 2015, 11:06:23 AM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty pageContext.request.userPrincipal}">
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <%response.sendRedirect("http://localhost:8080/CapstoneProject/admin");%>
    </c:if>
</c:if>
<!DOCTYPE html>
<html>
    <c:out value="${pageContext.request.userPrincipal.name}" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
         <link href="${pageContext.request.contextPath}/css/employeeStyles.css" rel="stylesheet">
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
                            <li role="presentation" class="active">
                                <a href="${pageContext.request.contextPath}/employee">Employee</a>
                            </li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/home">Home</a>
                            </li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log
                                    Out</a>
                            </li>
                        </ul>
                    </div>

                </div>
                <br>
                <div class = "row" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                    <h4>&nbsp;&nbsp;Create a Post:</h4>
                    <form role="form">

                        <div id="titlerow" class="row" >
                            <div class="form-group col-lg-1 col-md-1 col-sm-1">
                                <label >&nbsp;&nbsp;Title: </label>
                            </div>
                            <div class="form-group col-lg-5 col-md-5 col-sm-5">
                                <input id="title" type="text" name="title" placeholder=" title" >
                            </div>

                            <div class="form-group col-lg-2 col-md-2 col-sm-2 ">
                                <label >Author: </label>
                            </div>
                            <div class="form-group col-lg-offset-1 col-lg-3 col-md-3 col-sm-3">
                                <input  id="author" type="text" name="author" placeholder=" author">
                            </div>
                        </div>
                        <div id="titlerow" class="row" >
                            <div class="form-group col-lg-1 col-md-1 col-sm-1">
                                <label>&nbsp;&nbsp;Tags: &nbsp;</label>
                            </div>
                            <div class="form-group col-lg-5 col-md-5 col-sm-5">
                                <input id="tags" type="text" name="tags" placeholder=" tags comma separated">
                            </div> 

                            <div class="form-group col-lg-3 col-md-3 col-sm-3">
                                <label class="">Expiration Date: &nbsp;</label>
                            </div>
                            <div class="form-group col-lg-3 col-md-3 col-sm-3">
                                <input  class="" id="expiration-date" type="date" name="expiration-date" >
                            </div> 
                        </div>
                        <div id="titlerow" class="row" >
                            <div class="form-group col-lg-2 col-md-2 col-sm-2">
                                <label class="">&nbsp;&nbsp;Post Date: &nbsp;</label>
                            </div>
                            <div class="form-group col-lg-4 col-md-4 col-sm-4">
                                <input  class="" id="post-date" type="date" name="post-date" >
                            </div> 
                        </div>

                        <div class="form-group">
                            <textarea id="mytextarea"></textarea>
                        </div>

                        <div class="form-group">
                            <div>
                                <button type="submit"
                                        id="add-button"
                                        class="btn">
                                    Submit for Approval
                                </button>
                                <button type="submit"
                                        id="clear-button"
                                        class="btn ">
                                    Clear Input Fields
                                </button>
                            </div>
                        </div>
                        <div id="validationErrors" style="color: red"></div>
                    </form>

                    <table id="itemTable" class="table table-hover" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                        <tr>
                            <th width="35%">Title: </th>
                            <th width="35%">Author: </th>
                            <th width="10%">Edit: </th>
                            <th width="10%">Delete:</th>

                        </tr>
                        <tbody id="draftRows"></tbody>
                    </table>
                    <input type="hidden" id="editPostID" value="0">
                    <input type="hidden" id="editPostDate" value="">
                </div>
                <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
                <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                <script src="${pageContext.request.contextPath}/js/admin.js"></script>
                <script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
                <script type="text/javascript">
                    tinymce.init({
                        selector: "textarea",
                        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
                    });
                </script>

                </body>
                </html>
