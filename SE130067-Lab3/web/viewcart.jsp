<%-- 
    Document   : viewcart
    Created on : Oct 19, 2021, 10:50:16 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

        <title>View Cart Page</title>
    </head>
    <body>

        <c:if test="${sessionScope.USERID != null}">
            <font>
            Welcome, ${sessionScope.FULLNAME}
            </font>
        </c:if>
        <form action="MainController">
            <c:if test="${sessionScope.USERID == null}">
                <a href="login.html">Click here to Login </a>
            </c:if> 
            <c:if test="${sessionScope.USERID != null}">
                <input type="submit" value="Log Out" name="btAction" />
            </c:if> 
        </form>


        <h1>Here is your cart </h1>

        <form action="MainController">

            <c:set var ="hotelDTO" value="${sessionScope.HOTEL}"/>

            <h2> ${hotelDTO.hotelName} </h2> </br>

            <label for="datepicker">Check in date : </label>
            <input type="text" name="txtCheckInDate" id="datepicker1">

            <label for="datepicker">Check out date : </label>
            <input type="text" name="txtCheckOutDate" id="datepicker2"> </br> </br>

            <c:set var="cart" value="${sessionScope.CART}" />
            <input type="hidden" name="cart" value="${cart.items}" />
            <c:if test="${cart != null}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Room Name</th>
                            <th>Kind of Room</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:set var="total" value="0"/>
<!--                    <form action="MainController">-->
                        <c:forEach var="cartCus" items="${cart.items}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${cartCus.value.roomName}</td>
                                <td>${cartCus.value.kindOfRoom}</td>
                                <td>

                                    <input type="number" name="txtQuantity" value="${cartCus.value.amount}" min="1" max="1000"/>
                                    <!--<input type="hidden" name="txtQuantity" value="${cartCus.value.amount}" min="1" max="1000"/>-->
                                    <input type="hidden" name="txtRoomID" value="${cartCus.key}" />
                                    <input type="submit" value="Update Quantity" name="btAction" />
                        <!--</form>-->
                        <c:set var="errors" value="${requestScope.QUANTITYERR}" />
                        <c:if test="${not empty errors && cartCus.key == param.txtRoomID}">
                            <font color="red"> ${requestScope.QUANTITYERR} </font><br/>
                        </c:if>

                        </td>
                        <td>${cartCus.value.amount * cartCus.value.price}
                            <input type="hidden" name="txtPrice" value="${cartCus.value.amount * cartCus.value.price}" />
                            <c:set var="total" value="${total + cartCus.value.amount * cartCus.value.price}" />
                        </td>
                        <td> 
                            <!--<form action="MainController">-->
                                <input type="hidden" name="txtQuantity" value="${cartCus.value.amount}" min="1" max="1000"/>
                                <input type="hidden" name="txtRoomID" value="${cartCus.key}" />
                                <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" 
                                        onclick="return confirm('Do you want to Delete this product?')" name="btAction" value="Remove Items">
                                    Delete
                                </button>
                            <!--</form>-->
                        </td>
                        </tr>
                    </c:forEach>
                        
                    <tr>
                        <td colspan="4">
                            <a href="homepage.jsp">Add more Room To Cart </a>
                        </td>
                        <td colspan="2">
                            Total Price =  ${total}
                        </td>
                    </tr>
                    </tbody>

                </table> <br/>

                <c:set var="errors" value="${requestScope.INPUTDAYERR}" />
                <c:if test="${not empty errors}">
                    <font color="red"> ${requestScope.INPUTDAYERR} </font><br/>
                </c:if>
                <input type="submit" value="Booking" name="btAction" />

            </c:if>
            <c:if test="${cart == null}">
                <font color="red">
                <h2> Cart is empty</h2>
                </font>
            </c:if>
        </form>
    </body>
</html>
