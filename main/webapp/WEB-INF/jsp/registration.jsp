<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
        <style><%@include file="/WEB-INF/css/reg.css"%></style>
        <script type="text/javascript"><%@include file="/WEB-INF/js/reg.js"%></script>
</head>
<body>

         <div class="container">
           <h1 align="left" class="h1">Registration</h1>
           <p><h4 class="h4">Please fill in this form to create an account.</h4></p>
           <hr>
           <form action="Controller" method="post">
           <input type="hidden" name="command" value="Registration" />
           <label for="name"><b>Name</b></label>
           <input type="text" name="Name" value="" />
           <br/>
           <label for="login"><b>Login</b></label>
           <input type="text" name="Login" value="" />
           <br/>
           <label for="password"><b>Password</b></label>
           <input type="password" name="Password" value="" />
           <br/>
           <p><select name="Role" class="select">
            <option disabled>Choose role</option>
            <option>Student</option>
            <option>Teacher</option>
            <option>Admin</option>
           </select></p>
           <input type="submit" class="registerbtn">
           <br/>
           <div class="container signin">
           <p>Already have an account? <a href="GO_TO_SIGN_IN_PAGE">Sign in</a></p>
           <form>
         </div>
        <h3 align="center"><a href="index.jsp" class="return-to-main-button">Return to main page</a></h3>

        <c:if test="${not empty requestScope.errorMessage}">
            <h2><c:out value="16" /></h2>
        </c:if>
</body>
</html>