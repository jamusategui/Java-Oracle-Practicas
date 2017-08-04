<jsp:directive.page import="trader.*"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Customers</title>
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


        <%
	        Customer[] customers = (Customer[]) request.getAttribute("customers");
			if(customers!=null && customers.length>0){

		%>
	         	<table border="1" cellpadding="4">
            	<thead>
                <tr>
                    <th>Customer Id</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Portfolio</th>
                </tr>
            	</thead>
  	          	<tbody>

		<%		
	        	for (int i = 0; i < customers.length; i++) {
        %>
  	          	 <tr>
                    <td><a href="CustomerController?customerIdentity=<%= customers[i].getId()%>&submit=Get Customer"><%= customers[i].getId()%></a></td>
                    <td><%= customers[i].getName()%></td>
                    <td><%= customers[i].getAddr()%></td>
                    <td align='center'><a href='PortfolioController?customerIdentity=<%= customers[i].getId()%>'>View</a></td>
                </tr>
        <%
	        	}
		
		%>
		  		</tbody>
        	</table>
		<%
	        }else{
         %>
    	     <h3><font color='red'>No hay datos cargados</font></h3>
        <%
	       	}
         %>

        <jsp:scriptlet>
        <![CDATA[
            String message = (String) request.getAttribute("message");
            if (message != null) {
                out.println(String.format("<font color='red'>" + message + "</font>"));
            }
            ]]>
        </jsp:scriptlet> 


		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<c:forEach var="customerJSTL" items="${requestScope.customers}">
			<h5>${customerJSTL.name} ---- ${customerJSTL.addr}</h5>
		 </c:forEach>


</body>
</html>