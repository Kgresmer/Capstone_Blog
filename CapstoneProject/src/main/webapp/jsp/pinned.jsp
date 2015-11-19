<%-- 
    Document   : pinnedPost
    Created on : Nov 11, 2015, 10:03:05 AM
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
         <link href="${pageContext.request.contextPath}/css/pinnedStyles.css" rel="stylesheet">
        <title>Pinned Post Page</title>

    </head>
    <body>
        <h2></h2>
        <hr>
        <div class="container" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
            <br>
            <div class = "row" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                <div class="navbar navbar-default">
                    <ul class="nav navbar-nav">
                         <li role="presentation" class="active">
                            <a href="${pageContext.request.contextPath}/pinned">Pinned</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/home">Home</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/admin">Admin</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/userManagement">User Management</a>
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
                <div class="col-md-8"style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                    <form role="form"  method="post">
                        <div id="titlerow" class="row" >
                            <div class="form-group col-lg-1 col-md-1 col-sm-1">
                                <label>Title: &nbsp;</label>
                            </div>
                            <div class="form-group col-lg-4 col-md-4 col-sm-4">
                                <input id="title" type="text" name="title" placeholder="title">
                            </div> 

                            <div class="form-group col-lg-2 col-md-2 col-sm-2 ">
                                <label>Author: &nbsp;</label>
                            </div>
                            <div class="form-group col-lg-4 col-md-4 col-sm-4">
                                <input id="author" type="text" name="author" placeholder="author">
                            </div> 
                        </div>
                        <div id="titlerow" class="row" >
                            <div class="form-group col-lg-1 col-md-1 col-sm-1">
                                <label>Post Date:&nbsp;</label>
                            </div>
                            <div class="form-group col-lg-4 col-md-4 col-sm-4">
                                <input id="post-date" type="date" name="post-date">
                            </div> 
                            <div class="form-group col-lg-2 col-md-2 col-sm-2 ">
                                <label>Expiration:</label>
                            </div>
                            <div class="form-group col-lg-4 col-md-4 col-sm-4">
                                <input id="expiration-date" type="date" name="expiration-date" >
                            </div> 
                        </div>
                        <div class="form-group">
                            <textarea id="mytextarea" class="text"></textarea>
                        </div>
                        <button type="submit"
                                id="add-button2"
                                class="btn " style="margin-bottom: 12px;">
                            Save to Drafts
                        </button>
                        <button type="submit"
                                id="publish-button2"
                                class="btn " style="margin-bottom: 12px;">
                            Publish
                        </button>
                        <button type="submit"
                                id="un-publish-button2"
                                class="btn " style="margin-bottom: 12px;">
                            UnPublish
                        </button>
                        <button type="submit"
                                id="clear-button"
                                class="btn " style="margin-bottom: 12px;">
                            Clear Input Fields
                        </button>
                        <div id="validationErrors" style="color: red"></div>
                    </form>
                    <table id="itemTable" class="table table-hover" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                        <tr>
                            <th width="35%">Title: </th>
                            <th width="35%">Author: </th>
                            <th width="10%">Edit: </th>
                            <th width="10%">Delete:</th>

                        </tr>
                        <tbody id="draftPinRows"></tbody>
                    </table>
                    <input type="hidden" id="editPinID" value="0">
                    <input type="hidden" id="editPostDate" value="0">
                </div>
                <div class="col-md-4"style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                    <h3>Pinned Posts</h3>
                    <table id="itemTable" class="table table-hover" style="max-width:1080px; border:2px solid #ccc; margin-left: auto;margin-right: auto;">
                        <tr>
                            <th width="20%">Title</th>
                            <th width="20%">Author</th>
                            <th width="20%"></th>
                            <th width="20%"></th>
                            <th width="20%">Display Date</th>

                        </tr>    

                        <tbody id="activePinRows"></tbody>
                    </table>
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/admin.js"></script>
            <script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
            <script type="text/javascript">
                tinymce.init({
                    selector: "#mytextarea",
                    plugins: "save,image,preview",
                    toolbar: " undo redo | bold italic | styleselect | preview",
                    save_enablewhendirty: true,
                    save_onsavecallback: function () {
                        console.log("Save");
                    }

                });
            </script>
    </body>
</html>
