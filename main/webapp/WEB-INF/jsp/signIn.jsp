<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
    <title>Sign in</title>
    <style><%@include file="/WEB-INF/css/reg.css"%></style>
</head>

<body>

         <div class="container">
           <h1 align="left" class="h1">Sign in</h1>
           <p><h4 class="h4">Please fill in this form to sign in.</h4></p>
           <hr>
           <form action="Controller" method="post">
           <input type="hidden" name="command" value="Sign in" />
           <label for="login"><b>Login</b></label>
           <input type="text" name="Login" value="" />
           <br/>
           <label for="password"><b>Password</b></label>
           <input type="password" name="Password" value="" />
           <br/>
           <p><input type="submit" class="registerbtn">
           <br/>
           <div class="container signin">
           <p>Do not have an account? <a href="GO_TO_REGISTRATION_PAGE">Register now</a></p>
           </div>
           <br/>
           <a href="index.jsp" class="return-to-main-button">Return to main page.</a>
           <form>
         </div>

    <c:if test="${not empty requestScope.errorMessage}">
        <h2>
            <h2 align="center" class="h2"><c:out value="${requestScope.errorMessage}" /></a></h2>
        </h2>
    </c:if>

</body>
</html>