<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<script>
	
	 $(document).ready(function () {
		 calculateTotal();
	    });
	 function calculateTotal(){
		 var total = 0;
			$('.subtotal').each(function(index, obj) {
				var text = $(this).text();
				var amount = text.substring(1, text.length)
				total+=parseFloat(amount);
			});
			$('#finalTotal').html('$'+total.toFixed(2));
	 }
	
	
function changeInput( x, y) {
    
    var qty = x.value;
    var z= x.parentNode.parentNode; 
   	var label = z.childNodes[11];
   	//.childNodes[0];
    label.innerHTML = "$"+qty*y ;
    var total = 0;
	$('.subtotal').each(function(index, obj) {
		var text = $(this).text();
		var amount = text.substring(1, text.length)
		total+=parseFloat(amount);
	});
	$('#finalTotal').html('$'+total.toFixed(2));
    
/*         var c = z.parentNode.childNodes;
        var total = 0;
        var i;
        for (i = 0; i < c.length; i++) {
            var x = c.childNodes[11].innerHTML;
            var amount = x.substring(1, x.length);
            total+=parseInt(amount);
        }

        document.getElementById("demo").innerHTML = "$"+total; */
    
}



function removeCart(id) {
	$.ajax({
		url : "./removeCart/" + id,
		method : "get",
		success : function(data) {
			if (data.login == 0) {

			} else {
				var count = 0;
				$.each(data.products, function(key, value) {

					count++;
				})
				$('.aa-cart-notify').html(count);
				$('#productRow_' + id).remove();
				calculateTotal() ;
			}

		}
	})
}




</script>
	<%@ include file="/WEB-INF/views/header.jsp"%>
	<form action="/myshop/processCart" id="cartForm" method="POST">
		<table class="table">
			<thead>
				<tr>
					<th></th>
					<th>Product</th>
					<th>Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${products }">
					<tr class="cartRow" id="productRow_${product.productId }">
						<td><a class="remove"
							onclick="removeCart(${product.productId})"
							href="javascript:void(0);">remove</a></td>
						<td><a href="#"><img
								src="${pageContext.request.contextPath}${product.image}"
								alt="img"></a></td>
						<td><a class="aa-cart-title" href="#">${product.name }</a></td>
						<td>$${product.price }</td>
						<td><input name="quantity[${product.productId }]"
							class="aa-cart-quantity" type="number" value="1" min="1"
							max="${product.currQty}"
							onblur="changeInput(this,${product.price})" /></td>
						<td class="subtotal">$${product.price} <!-- <label>$${product.price}</label> -->
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>finalTotal</td>
					<td class="finalTotal" id="finalTotal"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><input type = "submit">Proceed to checkout</td>
				</tr>


			</tbody>
		</table>
	</form>
</body>
</html>


