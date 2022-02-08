<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
    <title>My courses</title>
    <style><%@include file="/WEB-INF/css/category.css"%></style>
<body>

 <h1 align="center" class="h1">My courses</h1>
 <br>
 <c:if test="${not empty requestScope.info}">
    <h2 align="center" class="description"><c:out value="${requestScope.info}"/></h2>
 </c:if>
 <br>
<c:if test="${not empty requestScope.userCourses}">
    <c:forEach var="course" items="${requestScope.userCourses}">
        <h3 align="center"><a href="Controller?command=ShowCourseInfo&courseID=${course.courseID}" class="animated-button"><c:out value="${course.courseTitle}"/></a></h3>
    </c:forEach>
</c:if>

<a href="Controller?command=GO_TO_PROFILE_PAGE" class="button_back">Go back</a>
<a href="index.jsp" class="return-to-main-button">Return to main page</a>

</body>
</html>