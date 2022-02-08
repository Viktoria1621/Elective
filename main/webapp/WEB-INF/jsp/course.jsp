<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<head>
    <title>All courses</title>
    <style><%@include file="/WEB-INF/css/category.css"%></style>
</head>

<body>


<c:if test="${not empty requestScope.courses}">
    <h1 align="center" class="h1">All courses</h1>
    <br>
    <c:forEach var="course" items="${requestScope.courses}">
        <h2 align="center"><a href="Controller?command=ShowCourseInfo&courseID=${course.courseID}"  class="animated-button"><c:out value="${course.courseTitle}"/></a></h2>
    </c:forEach>
</c:if>

<br>

<c:if test="${not empty requestScope.coursesByCategory}">
    <h1 align="center" class="h1"><c:out value="${categoryTitle}"/> courses</h1>
    <br>
    <c:forEach var="course" items="${requestScope.coursesByCategory}">
        <h2 align="center"><a href="Controller?command=ShowCourseInfo&courseID=${course.courseID}" class="animated-button"><c:out value="${course.courseTitle}"/></a></h2>
    </c:forEach>
    <h2 align="center"><a href="Controller?command=ShowCategories" class="button_back">Go back</a></h2>
</c:if>

<h2 align="center"><a href="index.jsp" class="return-to-main-button">Return to main page</a></h2>
</body>
</html>