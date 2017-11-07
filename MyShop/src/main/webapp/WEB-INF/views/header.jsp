<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Login Modal -->
	<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4>Login or Register</h4>
					<form:form action="login" modelAttribute="user">
						<label for="">Username<span>*</span></label>
						<form:input path="userName" />
						<label for="">Password<span>*</span></label>
						<form:password path="password" />
						<button class="aa-browse-btn" type="submit">Login</button>
						<label for="rememberme" class="rememberme"><input
							type="checkbox" id="rememberme"> Remember me </label>
						<p class="aa-lost-password">
							<a href="#">Lost your password?</a>
						</p>
						<div class="aa-register-now">
							Don't have an account?<a href="register">Register now!</a>
						</div>
					</form:form>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 id="modal_message">${message }</h4>


				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<div class="aa-header-top-right">
		<ul class="aa-head-top-nav-right">


			<%
				if (session.getAttribute("userId") == null) {
			%>

			<li><a href="javascript:void(0)" data-toggle="modal"
				data-target="#login-modal">Login</a></li>
			<li><a href="javascript:void(0)" data-toggle="modal"
				data-target="#login-modal">Register</a></li>
			<%
				} else {
			%>
			<li><a href="account.html">My Account</a></li>
			<!-- 							<li class="hidden-xs"><a href="wishlist.html">Wishlist</a></li> -->
			<li class="hidden-xs"><a href="${baseURL}/myshop/showCart">My
					Cart</a></li>
			<!-- 							<li class="hidden-xs"><a href="checkout.html">Checkout</a></li> -->
			<li><a href="${baseURL}/logout">Logout</a></li>
			<%
				}
			%>



		</ul>
	</div>
</body>
</html>