<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
    <title>Category</title>
    <style><%@include file="/WEB-INF/css/category.css"%></style>
</head>

<body>

<h1 align="center" class="h1">Category</h1>
<br>

<c:forEach var="category" items="${requestScope.categories}">
    <h2 align="center"><a href="Controller?command=ShowCoursesByCategory&categoryID=${category.categoryID}" class="animated-button"><c:out value="${category.categoryTitle}"/></a></h2>
</c:forEach>
<br>

<c:if test="${sessionScope.user.roleID eq 1}">
    <a href="Controller?command=GO_TO_UPDATE_CATEGORY_PAGE" class="update-button">Update categories</a>
</c:if>

<h2 align="center"><a href="index.jsp" class="return-to-main-button">Return to main page</a></h2>

</body>
</html>