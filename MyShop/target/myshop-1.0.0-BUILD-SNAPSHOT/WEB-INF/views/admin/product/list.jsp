<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="right_col" role="main">

	<!-- top tiles -->
	<div class="row tile_count" style="min-height: 700px;">
		<h1>All Products</h1>
		<table class="table table-responsive table-bordered datatable">
			<thead>
				<tr>
					<td>Name</td>
					<td>Image</td>
					<td>Unit Price</td>
					<td>Amount in Stock</td>
					<td>Category</td>
					<td>Actions</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${products}">
					<tr>
					<td>${product.name }</td>
					<td><img src="${pageContext.request.contextPath}${product.image}" width="50" height="50" /></td>
					<td>${product.price }</td>
					<td>${product.currQty }</td>
					<td>${product.category.name }</td>
					<td><a href="/myshop/admin/editProduct/${product.productId}">edit<i class="fa fa-pencil"></i></a>
						<a href="/myshop/admin/deleteProduct/${product.productId}">delete<i class="fa fa-trash"></i></a>
					</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>