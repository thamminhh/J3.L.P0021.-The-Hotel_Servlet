<%-- 
    Document   : bookinghistory
    Created on : Oct 20, 2021, 9:21:14 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking History Page</title>
    </head>
    <body>

        <h1>Booking History</h1>

        <font>
        Welcome, ${sessionScope.FULLNAME}
        </font>

        <form action="MainController">
            <input type="submit" value="Log Out" name="btAction" />
        </form>

        <c:set var ="historyDTO" value="${requestScope.HISTORY}"/>

        <c:if test="${historyDTO != null}">

            <c:forEach var="dto" items="${historyDTO}" varStatus="counter">
                Booking day : ${dto.bookingdate} </br>
                Check in day : ${dto.checkInDate} </br>
                Check out day: ${dto.checkOutDate} </br>
                Status : ${dto.status}
                
                  <c:set var ="roomDTO" value="${requestScope.ROOMBOOKED}"/>
                  <c:if test="${roomDTO != null}">
                      <c:forEach var="dto" items="${roomDTO}" varStatus="counter">
                          RoomID : ${dto.roomID} </br>
                          Quantity : ${dto.quantity} </br>
                      </c:forEach>
                  </c:if>
                  

            </c:forEach>   

        </c:if>
        <c:if test="${historyDTO == null}">
            No record found
        </c:if>
    </body>
</html>
