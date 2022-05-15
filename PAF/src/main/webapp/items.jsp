<%@ page import="model.customer" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>User Management </h1>
<form id="formItem" name="formItem">
 User Name: 
 <input id="customerName" name="customerName" type="text" 
 class="form-control form-control-sm">
 <br> User NIC: 
 <input id="customerNIC" name="customerNIC" type="text" 
 class="form-control form-control-sm">
 <br> User Address: 
 <input id="customerAddress" name="customerAddress" type="text" 
 class="form-control form-control-sm">
 <br> User E-mail: 
 <input id="customerEmail" name="customerEmail" type="text" 
 class="form-control form-control-sm">
 <br>
 <br> User Contact: 
 <input id="customerContact" name="customerContact" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 customer customerObj = new customer(); 
	 out.print(customerObj.readCustomers()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
