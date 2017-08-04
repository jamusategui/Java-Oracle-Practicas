<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="trader.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portfolio</title>
    </head>
    <body>
        <table border='1' cellpadding='4'>
            <tbody>
                <tr><td><a href='CustomerDetails.jsp'>Customer Details</a></td>
                <td><a href='AllCustomers'>All Customers</a></td>
                <td><a href='Stocks.xhtml'>Stocks</a></td></tr>
            </tbody>
        </table>
        <br/>
        <c:choose>
            <c:when test="${empty requestScope.message}">
                ${requestScope.customer.name}'s Stocks
                <br />
                <table border="1" cellpadding="4">
                    <thead>
                        <tr>
                            <th>Stock Symbol</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="share" items="${requestScope.shares}">
                            <tr>
                                <td>${share.stockSymbol}</td>
                                <td>${share.quantity}</td>
                            </tr>
                        </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <div style="color:red;">${requestScope.message}</div>
            </c:otherwise>
        </c:choose>
    </body>
</html>