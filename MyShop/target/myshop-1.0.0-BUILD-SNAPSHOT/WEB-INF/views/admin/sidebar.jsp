
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">

		<div class="navbar nav_title" style="border: 0;">
			<a href="index.html" class="site_title"><i class="fa fa-paw"></i>
				<span>MYSHOP</span></a>
		</div>
		<div class="clearfix"></div>

		<!-- menu prile quick info -->
		<div class="profile">
			<div class="profile_pic">
				<img src="${context}/myshop/resources/images/img.jpg" alt="..."
					class="img-circle profile_img">
			</div>
			<div class="profile_info">
				<span>Welcome,</span>
				<h2>John Doe</h2>
			</div>
		</div>
		<!-- /menu prile quick info -->

		<br />

		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

			<div class="menu_section">
				<h3>General</h3>
				<ul class="nav side-menu">
					<li><a href="/myshop/admin"><i class="fa fa-home"></i>
							Home </a></li>
					<li><a href="/myshop/admin/order"><i
							class="fa fa-shopping-cart"></i> Orders </a></li>

				</ul>


				<ul>
					<li><a href="/myshop/admin/category">All Categories</a></li>
					<li><a href="/myshop/admin/addCategory">Add Category</a></li>
					<li><a href="/myshop/admin/product">All Products</a></li>
					<li><a href="/myshop/admin/addProduct">Add Product</a></li>
					<li><a href="/myshop/admin/user">All User</a></li>
					<li><a href="/myshop/admin/addUser">Add User</a></li>

				</ul>
			</div>


		</div>
		<!-- /sidebar menu -->


		<!-- /menu footer buttons -->
	</div>
</div>

<!-- top navigation -->
<div class="top_nav">

	<div class="nav_menu">
		<nav class="" role="navigation">
			<div class="nav toggle">
				<a id="menu_toggle"><i class="fa fa-bars"></i></a>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="javascript:;"
					class="user-profile dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"> <img
						src="${context}/myshop/resources/images/img.jpg" alt="">John
						Doe <span class=" fa fa-angle-down"></span>
				</a>
					<ul
						class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">

						<li><a href="javascript:void(0);" onclick="logout()"><i
								class="fa fa-sign-out pull-right"></i> Log Out</a></li>
					</ul></li>
				<%@ taglib prefix="form"
					uri="http://www.springframework.org/tags/form"%>
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script>
					function logout() {
						// 						alert("click");
						document.getElementById("logoutForm").submit();
					}
				</script>




			</ul>
		</nav>
	</div>

</div>
<!-- /top navigation -->