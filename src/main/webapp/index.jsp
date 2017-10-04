<%@page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
	<form action="home" method="POST">
		<div class="form-group">
			<label for="username" class="col-2 col-form-label">Username</label>
			<div class="col-6">
				<input class="form-control" type="text" placeholder="Your username"
					name="username" id="username">
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-2 col-form-label">Password</label>
			<div class="col-6">
				<input class="form-control" type="search"
					placeholder="Your password" name="password" id="password">
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Login</button>
		<a href="/register" class="btn btn-primary" role="button">Register</a>
	</form>

</body>
</html>