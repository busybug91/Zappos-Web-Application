<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- For client side email validation jquery project Validity has been used-->
<html>
<head>
<style type="text/css">
#submit
{
	width:100%;
}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script type="text/javascript" src="js/jquery.validity.js"></script>
<script type="text/javascript "src="js/validateScript.js"></script>
<script type="text/javascript" src="js/sendInfo.js"></script>

<link rel="stylesheet" type="text/css" href="css/jquery.validity.css">
<title>Zappos- Notify Me
</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<table>
<tr>
<td>Email:</td>
<td><input type= "text" required name="email" id="email"></td>
</tr>
<tr>
<td>Product ID:</td>
<td><input type= "text" required name="prodID" id="prodID"></td>
</tr>
<tr><td>
<input value="Submit" type="button" id="submit" name="submit">
</td>
</tr>
</table>
<label id="submitStatus"> </label>
</body>
</html>