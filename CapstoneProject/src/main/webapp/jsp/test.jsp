<%-- 
    Document   : test
    Created on : Nov 6, 2015, 11:14:57 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <h1>Hello World!</h1>
        <script type="text/javascript">
            tinyMCE.init({
                // General options
                mode: "specific_textareas",
                editor_selector: "mceEditor"
            });
        </script>
        <form method="post" action="somepage">
            <textarea id="myarea1" class="mceEditor">This will be an editor.</textarea>
        </form>
        <button onclick="content()">Get content</button>

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/admin.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
    </body>


</html>
