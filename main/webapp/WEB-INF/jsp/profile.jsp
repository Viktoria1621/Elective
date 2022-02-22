<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
    <title>Profile</title>
    <style><%@include file="/WEB-INF/css/category.css"%></style>
<body>

 <h1 align="center" class="h1">Welcome to your profile</h1>
 <br>
 <h2 align="center" class="profile-info">Name: <c:out value="${sessionScope.user.name}"/></h2>
 <br>
 <h2 align="center" class="profile-info">Login: <c:out value="${sessionScope.user.login}"/></h2>
 <br>
 <h2 align="center" class="profile-info">Password: <c:out value="${sessionScope.user.password}"/></h2>
 <br>

 <a href="Controller?command=GO_TO_UPDATE_PROFILE_PAGE" class="edit-profile-button">Edit profile</a>
 <br>
 <c:if test="${sessionScope.user.roleID eq 3}">
    <a href="Controller?command=ShowMyCourses" class="my-courses-button">My courses</a>
 </c:if>
 <br>
 <c:if test="${sessionScope.user.roleID eq 2}">
    <a href="Controller?command=ShowMyCourses" class="my-courses-button">My courses</a>
 </c:if>

 <c:if test="${not empty requestScope.error}">
    <h2 class="h2"><c:out value="${error}"/></h2>
 </c:if>

 <h2 align="center"><a href="index.jsp" class="return-to-main-button">Return to main page</a></h2>

</body>
</html>