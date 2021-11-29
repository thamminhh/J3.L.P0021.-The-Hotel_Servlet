<%-- 
    Document   : register
    Created on : Oct 3, 2021, 7:28:37 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Create new account page</h1>

        <form action="MainController" method="POST">
            Email* <input type="text" name="txtEmail" value="${param.txtEmail}" /> <br/>
            <c:set var="errors" value="${requestScope.INSERTERR}" />
            <c:if test="${not empty errors.emailLengErr}">
                <font color="red"> ${errors.emailLengErr} </font><br/>
            </c:if>
            <c:if test="${not empty errors.emailIsExist}">
                <font color="red"> ${errors.emailIsExist} </font><br/>
            </c:if>
            <c:if test="${not empty errors.emailFormatErr}">
                <font color="red"> ${errors.emailFormatErr} </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="${param.txtPassword}" />(6 - 30 chars)<br/>
            <c:set var="errors" value="${requestScope.INSERTERR}" />
            <c:if test="${not empty errors.passwordLengErr}">
                <font color="red"> ${errors.passwordLengErr} </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="${param.txtConfirm}" /> <br/>
            <c:set var="errors" value="${requestScope.INSERTERR}" />
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red"> ${errors.confirmNotMatch} </font><br/>
            </c:if>
            Name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(2 - 50 chars)<br/>
            <c:set var="errors" value="${requestScope.INSERTERR}" />
            <c:if test="${not empty errors.fullnameLengErr}">
                <font color="red"> ${errors.fullnameLengErr} </font><br/>
            </c:if>
            <input type="submit" value="Create new Account" name="btAction"/>
            <input type="submit" value="Reset" />
        </form> <br/>

    </body>
</html>
