<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../resources/css/signin.css" rel="stylesheet">
    <script src="../../resources/js/jQuery.js"></script>
    <script src="../../resources/js/bootstrap.min.js"></script>
    <style>
        html, body, .container {
            height: 100%;
        }

        .container {
            display: table;
            vertical-align: middle;
        }

        .vertical-center-row {
            display: table-cell;
            vertical-align: middle;
        }
    </style>
    <title>Log in</title>
</head>
<body>
<c:url var="authorization" value="/login/auth"/>
<div class="container">
    <form:form action="${authorization}" class="form-signin" method="post" modelAttribute="user">

        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">User name</label>
        <form:input path="login" id="inputEmail" class="form-control" required="" autofocus=""/>
        <label for="inputPassword" class="sr-only">Password</label>
        <form:input type="password" path="pass" id="inputPassword" class="form-control" required=""/>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>

        <input  class="btn btn-lg btn-primary btn-block"type="submit" value="<spring:message text="Sign in"/>"/>

    </form:form>
</div>
</body>
</html>
