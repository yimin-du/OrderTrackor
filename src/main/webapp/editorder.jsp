<%@page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css">
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Order Trackor</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="home">Home</a></li>
				<li><a href="neworder">New Order</a></li>
				<li><a href="#">Other page</a></li>
				<li><a href="#">Other page</a></li>
			</ul>
		</div>
	</nav>

	<div class="content">
		<div class="order-form">
			<h2>New Order</h2>
			<form action="editorder" method="POST">
				<input type="hidden" name="orderId" value="${order.orderId}" />
				<div class="form-group">
					<label for="receivername" class="col-2 col-form-label">Receiver
						Name</label>
					<div class="col-6">
						<input class="form-control" type="text" placeholder=""
							name="receivername" id="receivername" value="${order.receiverName}" >
					</div>
				</div>
				<div class="form-group">
					<label for="receiverAddress" class="col-2 col-form-label">Receiver
						Address</label>
					<div class="col-6">
						<input class="form-control" type="search" placeholder=""
							name="receiveraddress" id="receiverAddress" value="${order.receiverAddress}" >
					</div>
				</div>

				<button type="submit" class="btn btn-primary">Update Order</button>
			</form>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>