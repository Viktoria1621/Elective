<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>

    <title>Welcome</title>
    <style><%@include file="/WEB-INF/css/welcome.css"%></style>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.welcome.message" var="message"/>
<fmt:message bundle="${loc}" key="local.welcome.sign" var="sign"/>
<fmt:message bundle="${loc}" key="local.welcome.reg" var="reg"/>
<fmt:message bundle="${loc}" key="local.welcome.courses" var="courses"/>
<fmt:message bundle="${loc}" key="local.welcome.categories" var="categories"/>
<fmt:message bundle="${loc}" key="local.welcome.signout" var="signout"/>
<fmt:message bundle="${loc}" key="local.welcome.profile" var="profile"/>
<fmt:message bundle="${loc}" key="local.welcome.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.welcome.locbutton.name.en" var="en_button"/>

<body>

<h1 align="center" class="h1"><c:out value="${message}"/></h1>
<br>
<br>
<c:if test="${empty sessionScope.user}">
    <a href="GO_TO_SIGN_IN_PAGE" class="animated-button"><c:out value="${sign}"/></a>
    <br>
    <a href="GO_TO_REGISTRATION_PAGE" class="animated-button"><c:out value="${reg}"/></a>
    <br>
</c:if>
<a href="Controller?command=ShowAllCourses" class="animated-button"><c:out value="${courses}"/></a>
<br>
<a href="Controller?command=ShowCategories" class="animated-button"><c:out value="${categories}"/></a>

<form action="Controller" method="post">
    <c:set var="local" scope="request" value="ru"/>
    <input type="hidden" name="command" value="Change" />
    <input type="submit" value="${ru_button}" class="ru-button"/>
</form>

<form action="Controller" method="post">
    <input type="hidden" name="local" value="en" />
    <input type="hidden" name="command" value="Change" />
    <input type="submit" value="${en_button}" class="en-button"/> <br/>
</form>

<c:if test="${sessionScope.user.roleID eq 2 or sessionScope.user.roleID eq 3}">
    <a href="Controller?command=GO_TO_PROFILE_PAGE" class="profile-button"><c:out value="${profile}"/></a>
</c:if>
<c:if test="${not empty sessionScope.user}">
    <a href="Controller?command=GO_TO_SIGN_OUT_PAGE" class="sign-out-button"><c:out value="${signout}"/></a>
</c:if>

</body>
</html>