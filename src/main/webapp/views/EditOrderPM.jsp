<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page
	import="org.springframework.context.support.AbstractApplicationContext"%>
<%-- <%
	response.addHeader("Refresh", "10");
%> --%>
<%@page import="com.mock.project.config.AppConfig"%>
<%@page
	import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@ page import="java.util.List"%>
<%@ page import="com.mock.project.model.Order"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page isELIgnored="false"%><!DOCTYPE html>
<html>
   <head>
         <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <style>
   #creat-order-form{
    width:80%;
    border: 
}
.form-input-label{
    margin-bottom: 10px;
}
.form-input-fields{
    width:auto;
    margin-bottom: 10px;
    border-radius: 10px;
    border: 1px solid grey;
    padding:4px;
}
.form-input-fields input, select{
    border:none;
    background-color: transparent;
}
.form-input-fields select:onfocus{
    border: none;
}
input[type="number"]:disabled {
    background-color:grey;
    border-radius: 10px;
    opacity: 0.3;
}
textarea:focus, select:focus, input:focus{
    outline: none;
}
$dia: 6em;
$outcolor: #fff;
$bdrwidth: 10px;
$time: 0.15s;
   </style>
   <script>
   function changetextbox(obj)
   {  
        var limit = document.getElementById("limit");
        var stop = document.getElementById("stop");
       	stop.disabled =(obj.value == "limit" || obj.value == "market");
		limit.disabled =(obj.value == "stop" || obj.value == "market");
		limit.value="";
		stop.value="";
   }
   
   
   $( document ).ready(function() {
	   var i = document.getElementById("hidden").value;
	   var id = document.getElementById(i);
	   id.selected=true;
	});
 
</script>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <title>Trading Application</title>
     </head>
   <body >
      <nav class="navbar navbar-default">
      <div class="container-fluid">
         <div class="navbar-header">
            <a class="navbar-brand" href="./PMHome.jsp">Portfolio Manager</a>
         </div>
         <ul class="nav navbar-nav">
             <li><a href="./PMHome.jsp">Home</a></li>
            <li ><a href="./CreateTrade.jsp">Create Order</a></li>
            <li ><a href="ViewOrderBlotter">Order Blotter</a></li>
            <li ><a href="ViewPendingOrder">Pending Orders</a></li>
            <li><a href="GeneralView">History</a></li>
         </ul>
      </div>
    </nav>
       <div id="creat-order-form" class="container well">
         <h2 id="eqorder" >Edit Equity Order</h2>       
           <%Order o=(Order)request.getSession().getAttribute("Orders");%>
	       <% System.out.println(request.getSession().getAttribute("Orders"));
	       System.out.println(o.getOrderId());%>
            <form action="EditOrder" method="post" >
               
                <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Order Id: </div>
                <div class="form-input-fields col col-sm-8"><input type="text" id="orderId" name="orderId1" value='<%=o.getOrderId() %>' readonly/></div>
               
                <div id="" class="form-input-label col col-sm-4"> Symbol: </div>
               <div class="form-input-fields col col-sm-8"><input type="text" id="symbol" name="symbol1" value='<%=o.getSymbol() %>' readonly/></div>
                  
                <div id="" class="form-input-label col col-sm-4"> Side: </div>
               <div class="form-input-fields col col-sm-8"><input type = "text" id="side" name="side1" value='<%=o.getSide() %>' readonly>
              </div>
                
                <div id="" class="form-input-label col col-sm-4"> Order Type: </div>
               <div class="form-input-fields col col-sm-8">
               <select id="orderType" name="orderType1">
               	<option value="market" id="market"> Market</option>
                  <option value="limit" id="limit"> Limit</option>
                  <option value="stoplimit" id="stoplimit"> Stop-Limit</option>
                  <option value="stop" id="stop"> Stop </option>
               </select>
               </div>
                
                <div id="" class="form-input-label col col-sm-4"> Order Qualifier: </div>
               <div class="form-input-fields col col-sm-8"><input type="text" id="orderqual" name="orderqual1" value='<%=o.getQualifier() %>'>
                  </div>
                 <input type="hidden" id="hidden" value='<%=o.getOrderType()%>'/> 
                <div id="" class="form-input-label col col-sm-4"> Account Type: </div>
               <div class="form-input-fields col col-sm-8"><input type="text" id="acctype" name="acctype1" value='<%=o.getAccountType() %>'>
                  </div>
                <div id="" class="form-input-label col col-sm-4"> Quantity: </div>
               <div class="form-input-fields col col-sm-8"><input type="number" id="qtyPlaced" name="qtyPlaced1" value='<%=o.getQtyPlaced() %>'>
                  </div>
                <div id="" class="form-input-label col col-sm-4"> Trader: </div>
               <div class="form-input-fields col col-sm-8"><input type="text" id="trader" name="trader1" value='<%=o.getTraderId() %>'>
                 </div>
                <div id="" class="form-input-label col col-sm-4"> Portfolio: </div>
               <div class="form-input-fields col col-sm-8"><input type = "text" id="portfolio" name="portfolio1" value='<%=o.getPortfolioId() %>'>
                   </div>
                <div id="stop" class="form-input-label col col-sm-4"> Stop Price: </div>
               <div class="form-input-fields col col-sm-8"><input type="number" id="stop" name="stop1" value='<%=o.getStopPrice() %>'></div>
                 <div id="limit" class="form-input-label col col-sm-4"> Limit Price: </div>
               <div class="form-input-fields col col-sm-8"><input type="number" id="limit" name="limit1" value='<%=o.getLimitPrice() %>'></div>
                   </div>
             <input type="submit" class="btn btn-default"  value="Save"/>
             
             
            </form>
        
         </div>
 </body>
</html>