<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile settings</title>
        <style><%@include file="/WEB-INF/css/reg.css"%></style>
        <script type="text/javascript"><%@include file="/WEB-INF/js/reg.js"%></script>
</head>
<body>

         <div class="container">
           <h1 align="center" class="h1">Profile settings</h1>
           <p><h4 class="description" align="center">Here you can edit your profile.
           Please enter new name, login or password in this form, if you want to change it.</h4></p>
           <hr>
           <form action="Controller" method="post">
           <input type="hidden" name="command" value="ProfileEditing" />
           <label for="name"><b>Enter new name</b></label>
           <input type="text" name="Name" value="" />
           <br/>
           <label for="login"><b>Enter new login</b></label>
           <input type="text" name="Login" value="" />
           <br/>
           <label for="password"><b>Enter new password</b></label>
           <input type="password" name="Password" value="" />
           <br/>
           <input type="submit" class="registerbtn">
           <form>
         </div>
        <h2 align="center"><a href="Controller?command=GO_TO_PROFILE_PAGE" class="button_back">Go back</a></h2>
        <h3 align="center"><a href="index.jsp" class="return-to-main-button">Return to main page</a></h3>
</body>
</html>