<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Apply Loan Here</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background-image:
		url("https://static.vecteezy.com/system/resources/previews/000/621/376/original/isometric-bank-building-business-and-financial-concept-futuristic-3d-bank-with-box-isolated-on-background-vector.jpg");
	background-size: cover;
	background-position: center;
}

.container {
	max-width: 400px;
	padding: 30px;
	background-color: rgba(255, 255, 255, 0.9);
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
	text-align: center;
}

h1 {
	color: #3366cc;
	margin-bottom: 20px;
}

p {
	margin-bottom: 20px;
}

label {
	font-weight: bold;
}

form {
	margin-top: 20px;
}

input[type="text"] {
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
	width: 100%;
	margin-top: 5px;
}

input[type="submit"] {
	background-color: #3366cc;
	color: #fff;
	border: none;
	padding: 10px 20px;
	border-radius: 4px;
	font-weight: bold;
	cursor: pointer;
	transition: background-color 0.4s ease;
}

input[type="submit"]:hover {
	background-color: #005580;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Apply for a Loan</h1>
		<p>Select the type of loan you want:</p>
		<p>1. Home Loan</p>
		<p>2. Education Loan</p>
		<p>3. Vehicle Loan</p>
		<p>4. Gold Loan</p>
		<p>5. Personal Loan</p>
		<form action="Loan" method="post">
			<label>Enter your Choice:</label> <input type="text" name="text">
			<input type="submit" value="Submit">
		</form>
	</div>
</body>
</html>