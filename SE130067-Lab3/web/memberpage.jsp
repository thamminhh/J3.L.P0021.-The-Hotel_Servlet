<%-- 
    Document   : memberpage
    Created on : Oct 18, 2021, 3:19:58 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Page</title>
    </head>
    <body>
        <h1>Member Page</h1>
        
         <font>
        Welcome, ${sessionScope.FULLNAME}
        </font>
        
        <form action="MainController">
            <input type="submit" value="Log Out" name="btAction" />
        </form>
        
         <form action="MainController">                              
            Search Hotel: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br/><br/>

            Search Option <select name="searchOption" >
                <c:set var="option" value="${param.searchOption}"/>
                <option <c:if test="${option == 'Hotel Name' }" >selected</c:if> >Hotel Name</option>
                <option <c:if test="${option == 'Hotel Area' }" >selected</c:if> >Hotel Area</option>
                <option <c:if test="${option == 'Hotel Star' }" >selected</c:if> >Hotel Star</option>
            </select><br/><br>     

            <input type="submit" value="Search Hotel" name="btAction"/>
                <a href="viewcart.jsp">View Your Cart</a>

            </form> <br>


        <c:set var ="result" value="${requestScope.RESULT}"/>

        <c:if test="${result != null}">


            <c:forEach var="dto" items="${result}" varStatus="counter">

                <input type="hidden" name="txtHotelID" value="${dto.hotelID}" />

                <c:url var="urlRewriting" value="MainController" >
                    <c:param name="txtHotelID" value="${dto.hotelID}"/>
                    <c:param name="btAction" value="viewRoom"/>
                </c:url>
                <a href="${urlRewriting}"> <h2> ${dto.hotelName} </h2> </a>


                Star: ${dto.star} </br>

                Address: ${dto.address}</br>


            </c:forEach>
        </c:if> 
        <c:if test="${result == null}">
            No record found
        </c:if>
        
    </body>
</html>
