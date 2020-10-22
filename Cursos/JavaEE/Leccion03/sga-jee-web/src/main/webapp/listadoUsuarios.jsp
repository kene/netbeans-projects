<%-- 
    Document   : listadoUsuarios
    Created on : 9/01/2020, 10:00:07 AM
    Author     : macos
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Usuarios</title>
    </head>
    <body>
        <h1>Listado Usuarios</h1>
        <ul>
            <c:forEach items="${usuarios}" var="usuario">
                <li>${usuario.username}</li>
            </c:forEach>
        </ul>
    </body>
</html>
