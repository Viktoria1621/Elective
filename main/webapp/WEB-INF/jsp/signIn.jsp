<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<head>
    <title>Sign in</title>
    <style><%@include file="/WEB-INF/css/reg.css"%></style>
</head>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.sign.name" var="name"/>
<fmt:message bundle="${loc}" key="local.sign.message" var="message"/>
<fmt:message bundle="${loc}" key="local.sign.login" var="login"/>
<fmt:message bundle="${loc}" key="local.sign.password" var="password"/>
<fmt:message bundle="${loc}" key="local.sign.account" var="account"/>
<fmt:message bundle="${loc}" key="local.sign.gotoreg" var="gotoreg"/>
<fmt:message bundle="${loc}" key="local.returntomain" var="returntomain"/>
<fmt:message bundle="${loc}" key="local.welcome.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.welcome.locbutton.name.en" var="en_button"/>

<body>

         <div class="container">
           <h1 align="left" class="h1"><c:out value="${name}"/></h1>
           <p><h4 class="h4"><c:out value="${message}"/></h4></p>
           <hr>
           <form action="Controller" method="post">
           <input type="hidden" name="command" value="Sign in" />
           <label for="login"><b><c:out value="${login}"/></b></label>
           <input type="text" name="Login" value="" />
           <br/>
           <label for="password"><b><c:out value="${password}"/></b></label>
           <input type="password" name="Password" value="" />
           <br/>
           <p><input type="submit" class="registerbtn">
           <br/>
           <div class="container signin">
           <p><c:out value="${account}"/><a href="GO_TO_REGISTRATION_PAGE"><c:out value="${gotoreg}"/></a></p>
           </div>
           <br/>
           <a href="index.jsp" class="return-to-main-button"><c:out value="${returntomain}"/></a>
           <form>
         </div>

    <c:if test="${not empty requestScope.errorMessage}">
        <h2>
            <h2 align="center" class="h2"><c:out value="${requestScope.errorMessage}" /></a></h2>
        </h2>
    </c:if>

    <form action="Controller" method="post">
        <c:set var="local" scope="request" value="ru"/>
        <input type="hidden" name="command" value="Change" />
        <input type="submit" value="${ru_button}" class="ru-button"/>
    </form>
    <form action="Controller" method="post">
        <input type="hidden" name="local" value="en" />
        <input type="hidden" name="command" value="Change" />
        <input type="submit" value="${en_button}" class="en-button"/>
    </form>

</body>
</html>