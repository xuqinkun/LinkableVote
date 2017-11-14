<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
						+ request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<style type="text/css">
	div.errors {
		background-color: #ffcccc;
		border: 2px solid red;
	}
	div.groupControl {
		display:block;
	}
	span[id$="Error"] {
		color:red;
	}
</style>
<script type="text/javascript" src="<%=basePath %>assets/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>assets/myjs/ajax.js"></script>

</head>
<body>
	<h1>Register</h1>
	<form  method="post" id="register-form" >
		<div class="groupControl">
			First Name: <input name="firstName" />
			<span  id="firstNameError"></span>
		</div>
		<div class="groupControl">
			Last Name: <input name="lastName" />
			<span  id="lastNameError"></span>
		</div>
		<div class="groupControl">
			UserName: <input name="username" />
			<span  id="usernameError"></span>
		</div>
		<div class="groupControl">
			Password: <input name="password" />
			<span  id="passwordError"></span>
		</div>
		<div class="groupControl">
			E-mail <input name="email"/>
			<span  id="emailError"></span>
		</div>
		<input type="submit" value="register"/>
	</form>
	
	<script type="text/javascript">
		function collectData(fields) {
			var data = {};
			for (var i = 0; i < fields.length; i++) {
				var item = $(fields[i]);
				data[item.attr('name')] = item.val();
			}
			return data;
		}
		$("document").ready(function(){
			$("#register-form").bind("submit",function(e){
				e.preventDefault();
				var inputs = $("#register-form").find("input");
				var data = collectData(inputs);
				var params = {
					url : "<%=basePath%>spitter/register",
					type: "post",
					dataType: "json",
					data: data
				};
				var succCallback = function(data) {
					console.log("status:" + data.status)
					$.each(data.errorList, function(index, value) {
						var item = $("#"+value.fieldName + "Error");
						item.html(value.errorMessage);
					});
				}
				new myAjax(params, succCallback);
			});
		});
	</script>
</body>
</html>
