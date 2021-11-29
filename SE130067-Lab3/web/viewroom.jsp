<%-- 
    Document   : viewdetail
    Created on : Oct 19, 2021, 11:54:27 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker1").datepicker();
            });
        </script>
        <script>
            $(function () {
                $("#datepicker2").datepicker();
            });
        </script>
        <title>View Room Page</title>
    </head>
    <body>
        <h1> View Room </h1>

        <c:if test="${sessionScope.USER != null}">
            <font>
            Welcome, ${sessionScope.FULLNAME} </br>
            </font>
        </c:if>

        <c:if test="${sessionScope.USER == null}">
            <a href="login.html">Click here to login</a> </br>
        </c:if> 
        <c:if test="${sessionScope.USER != null}">
            <form action="MainController">
                <input type="submit" value="Log Out" name="btAction" />
            </form>
        </c:if> 
            <a href="viewcart.jsp">View Your Cart</a> </br>

        <form action="MainController">

            <c:set var ="hotelDTO" value="${requestScope.HOTEL}"/>

            <h2> ${hotelDTO.hotelName} </h2> </br>

            <label for="datepicker">Check in date : </label>
            <input type="text" name="txtCheckInDate" id="datepicker1">

            <label for="datepicker">Check out date : </label>
            <input type="text" name="txtCheckOutDate" id="datepicker2"> </br> </br>

            <c:set var="errors" value="${requestScope.INPUTDAYERR}" />
            <c:if test="${not empty errors}">
                <font color="red"> ${requestScope.INPUTDAYERR} </font><br/>
            </c:if>


            <c:set var ="days" value="${requestScope.DAYS}"/>  


            Search Option <select name="searchRoomOption" >
                <c:set var="option" value="${param.searchRoomOption}"/>
                <option <c:if test="${option == 'Single' }" >selected</c:if> >Single</option>
                <option <c:if test="${option == 'Double' }" >selected</c:if> >Double</option>
                <option <c:if test="${option == 'Family' }" >selected</c:if> >Family</option>
                </select><br/><br>

            <input type="hidden" name="txtHotelID" value="${hotelDTO.hotelID}"/>
            <input type="submit" value="Search Room" name="btAction"/> </br> </br>

            <c:set var ="roomDTO" value="${requestScope.ROOM}"/>
            <c:if test="${roomDTO != null}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Kind Of Room</th>
                            <th>Price</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <c:forEach var="dto" items="${roomDTO}" varStatus="counter">
                        <tbody>

                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.roomName}
                                </td>
                                <td> ${dto.kindOfRoom}
                                </td>
                                <td>
                                    <c:if test="${not empty days}">
                                        ${dto.price * days}
                                    </c:if>
                                    <c:if test="${empty days }">
                                        ${dto.price}
                                    </c:if>

                                </td>
                                <td>${dto.status}</td>
                                <td>
                                    <form action="MainController">
                                        <input type="submit" value="Add To Cart" name="btAction" />
                                        <input type="hidden" name="txtRoomID" value="${dto.roomID}" />
                                        <input type="hidden" name="searchRoomOption" value="${param.searchRoomOption}"/> 
                                        <input type="hidden" name="txtHotelID" value="${hotelDTO.hotelID}"/>
                                        <input type="hidden" name="txtCheckInDate" id="datepicker1">
                                        <input type="hidden" name="txtCheckOutDate" id="datepicker2"> 
                                    </form>
                                </td>
                            </tr>
                        </tbody>
                    </c:forEach>
                        
                </table>

                <c:if test="${roomDTO == null}">
                    <font color="red">
                    <h2> No record found </h2>
                    </font>
                </c:if>
            </c:if>
        </form>
    </body>
</html>
