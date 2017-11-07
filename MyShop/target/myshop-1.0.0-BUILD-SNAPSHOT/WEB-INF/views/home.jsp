<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />
<html>
<head>
	<title>Home</title>
</head>
<body>
 <%@ include file="/WEB-INF/views/header.jsp"%>
	<table border =2>
	<c:forEach items="${products}" var="product">
	<tr>
		<td><a href="javascript:void(0)" onclick="addCart(${product.productId})">Add To Cart</a></td>
		<td><a href="showProduct/${product.productId}"><img src="${pageContext.request.contextPath}${product.image}"></a></td>
		<td><a href="showProduct/${product.productId}">${product.name}</a></td>
		<td>${product.price}</td>
		<td>${product.description }</td>
	<tr>
	</c:forEach>
	</table>
</body>
</html>
