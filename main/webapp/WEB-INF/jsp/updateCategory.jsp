<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
    <title>Update categories</title>
    <style><%@include file="/WEB-INF/css/reg.css"%></style>
    <script type="text/javascript"><%@include file="/WEB-INF/js/reg.js"%></script>
</head>

<body>
         <div class="container">
           <h1 align="center" class="h1">Update categories</h1>
           <p><h4 class="description" align="center">Here you can update categories.
           Please enter category title to add or delete it.</h4></p>
           <hr>
           <form action="Controller" method="post">
           <input type="hidden" name="command" value="UpdateCategory" />
           <label for="AddCategory"><b>Add category</b></label>
           <input type="text" name="AddCategory" value="" />
           <br/>
           <label for="DeleteCategory"><b>Delete category</b></label>
           <input type="text" name="DeleteCategory" value="" />
           <br/>
           <input type="submit" class="registerbtn">
           <form>
         </div>

<h2 align="center"><a href="Controller?command=ShowCategories" class="button_back">Go back</a></h2>
<h2 align="center"><a href="index.jsp" class="return-to-main-button">Return to main page</a></h2>
</body>
</html>