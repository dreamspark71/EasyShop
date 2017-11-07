<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <%@ include file="/WEB-INF/views/header.jsp"%>
	<table>
		<tr>
			<td>${product.name}</td>
		</tr>
		<tr>
			<td>${product.price}</td>
		</tr>
		<tr>
			<td>${product.description}</td>
		</tr>
		<tr>
			<td><img
				src="${pageContext.request.contextPath}${product.image}"></td>
		</tr>
		<tr>
			<td><input type="number" min="1" max="${product.quantity}"
				value="1" id="quantity" /></td>
		</tr>
		<tr>
			<td><input type="hidden" value="${product.productId}"
				id="productID" /></td>
		</tr>
		<tr>
			<td><input type="button" id="addToCartButton"
				value="Add To Cart"></td>
		</tr>
		<tr>
			<td><a href="#" class="add">Add To Cart</a></td>
		</tr>
	</table>
	<script>
		$(document).on('click', ".add", function(event) {
			event.preventDefault();
			var id=${product.productId};

			$.ajax({

				url : "./addCart/"+id ,
				type : 'get',
				success : function(data) {
					if (data.login == 0) {
						$('#modal_message').html("Please login to add to cart");
						$('#messageModal').modal('show');
					} else {
						var count = 0;
						$.each(data.products, function(key, value) {
							// console.log(value);
							count++;
						})
						$('.aa-cart-notify').html(count);
						// console.log(data.products[0]);
						$('#modal_message').html("added successfully");
						$('#messageModal').modal('show');
					}
					$('#quick-view-modal_' + id).modal('hide');
					
				}
			})
		});
			</script>


</body>
</html>