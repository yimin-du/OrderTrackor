<%@page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Order Trackor</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="neworder">New Order</a></li>
				<li><a href="#">Other page</a></li>
				<li><a href="#">Other page</a></li>
			</ul>
		</div>
	</nav>
	
	<h2>New Order</h2>
	<form action="neworder" method="POST">
		<c:if test="${orderCreated}">
			<p>You just placed an new order.</p>
		</c:if>
		<div class="form-group">
			<label for="username" class="col-2 col-form-label">Receiver Name</label>
			<div class="col-6">
				<input class="form-control" type="text" placeholder=""
					name="receivername" id="receivername">
			</div>
		</div>
		<div class="form-group">
			<label for="text" class="col-2 col-form-label">Receiver Address</label>
			<div class="col-6">
				<input class="form-control" type="search"
					placeholder="" name="address" id="address">
			</div>
		</div>
		
		<button type="submit" class="btn btn-primary">Create Order</button>
	</form>
</body>
</html>