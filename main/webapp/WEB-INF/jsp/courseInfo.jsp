<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
    <title>Course info</title>
    <style><%@include file="/WEB-INF/css/category.css"%></style>
</head>

<body>

<h1 align="center" class="h1"><c:out value="${requestScope.courseInfo.courseTitle}"/></h1>
<br>
<h4 align="center" class="description"><c:out value="${requestScope.courseInfo.courseDescription}"/></h4>
<br>
<h2 align="center" class="h2">Course duration: <c:out value="${courseInfo.courseDuration}"/> hours.</h2>
<br>

<c:if test="${sessionScope.user.roleID eq 3}">
    <h2 align="center"><a href="GO_TO_SIGN_IN_PAGE" class="animated-button">Join now</a></h2>
</c:if>
<br>
<c:if test="${empty sessionScope.user}">
    <h2 align="center"><a href="GO_TO_SIGN_IN_PAGE" class="animated-button">Sign in or register to join course</a></h2>
</c:if>

<h2 align="center"><a href="index.jsp" class="return-to-main-button">Return to main page</a></h2>

</body>
</html>