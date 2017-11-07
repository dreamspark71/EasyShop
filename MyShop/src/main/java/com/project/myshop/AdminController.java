package com.project.myshop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.pojo.Administrator;
import com.project.dao.CategoryDao;
import com.project.dao.OrderDao;
import com.project.dao.ProductDao;
import com.project.dao.UserDao;
import com.project.pojo.*;


@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("userDao")
	UserDao userDao;
	
	@Autowired
	@Qualifier("orderDao")
	OrderDao orderDao;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDao categoryDao;
	
	@Autowired
	@Qualifier("productDao")
	ProductDao productDao;
	
	
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String processAdminLogin(@ModelAttribute("login") Administrator admin, Model model) {
		String username = admin.getUserName();
		String password = admin.getPassword();
		return "redirect:/adminHome";
	}
	
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String adminCategory(Model model) {
		List<Category> categories = categoryDao.getCategoriess();

		model.addAttribute("categories", categories);
		model.addAttribute("page", "category/list.jsp");
		return "admin/index";
	}

	@RequestMapping(value = "/admin/addCategory", method = RequestMethod.GET)
	public String adminAddCategory(Model model) {
		Category cat = new Category();
		model.addAttribute(cat);
		model.addAttribute("page", "category/add.jsp");
		return "admin/index";
	}

	@RequestMapping(value = "/admin/addCategory", method = RequestMethod.POST)
	public ModelAndView saveAddCategory(@Valid Category category, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		
		if (!result.hasErrors()) {
			
			categoryDao.createCategory(category);
			
			model.addObject("page", "category/list.jsp");
			model.setViewName("redirect:/admin/category");
			redirectAttributes.addFlashAttribute("message", "Category Added Successfully..");

		} else {
			model.addObject("page", "category/add.jsp");
			model.setViewName("admin/index");
		}
		return model;

	}

	@RequestMapping(value = "/admin/editCategory/{id}", method = RequestMethod.GET)
	public String editAdminCategory(Model model, @PathVariable long id) {

		Category cat = categoryDao.findCategoryById(id);
		model.addAttribute(cat);
		model.addAttribute("page", "category/add.jsp");
		return "admin/index";
	}

	@RequestMapping(value = "/admin/editCategory/{id}", method = RequestMethod.POST)
	public String updateAdminCategory(Model model, @PathVariable long id, @ModelAttribute("category") Category category,
			final RedirectAttributes redirectAttributes) {

		Category cat = categoryDao.findCategoryById(id);
		cat.setName(category.getName());
		categoryDao.createCategory(cat);
		redirectAttributes.addFlashAttribute("message", "Category is updated successfully");

		return "redirect:/admin/category";
	}

	@RequestMapping(value = "/admin/deleteCategory/{id}", method = RequestMethod.GET)
	public String deleteAdminCategory(Model model, @PathVariable long id, final RedirectAttributes redirectAttributes) {

		Category cat = categoryDao.findCategoryById(id);
		categoryDao.deleteCategory(cat);
		redirectAttributes.addFlashAttribute("message", "Category is deleted successfully");

		return "redirect:/admin/category";
	}
	
	@RequestMapping(value = "/admin/product", method = RequestMethod.GET)
	public String adminProduct(Model model) {
		List<Product> products = productDao.getProductsForHomePage();
		//List<Product> products = productDao.findByCategory(4L);
		model.addAttribute("products", products);
		model.addAttribute("page", "product/list.jsp");
		return "admin/index";
	}

	@RequestMapping(value = "/admin/addProduct", method = RequestMethod.GET)
	public String adminAddProduct(Model model) {
		Product pro = new Product();
		model.addAttribute(pro);
		model.addAttribute("page", "product/add.jsp");

		List<Category> categories = categoryDao.getCategoriess();
		model.addAttribute("categories", categories);
		return "admin/index";
	}

	@RequestMapping(value = "/admin/addProduct", method = RequestMethod.POST)
	public ModelAndView saveAddProduct(@Valid Product product, BindingResult result, @RequestParam("cat") Long catId,
			final RedirectAttributes redirectAttributes) throws IOException {

		ModelAndView model = new ModelAndView();
		
		if (!result.hasErrors()) {
			
			
			Category proCat = categoryDao.findCategoryById(catId);
			product.setCategory(proCat);
			productDao.createProduct(product);
			
			
			model.addObject("page", "product/list.jsp");
			model.setViewName("redirect:/admin/product");;
			redirectAttributes.addFlashAttribute("message", "Product Added Successfully..");
		}
		else{
			List<Category> categories = categoryDao.getCategoriess();
			model.addObject("categories", categories);
			model.addObject("page", "product/add.jsp");
			model.setViewName("admin/index");
		}
		
		return model;
	}

	@RequestMapping(value = "/admin/editProduct/{id}", method = RequestMethod.GET)
	public String updateProduct(Model model, @PathVariable long id){
		Product pro = productDao.findProductById(id);
		model.addAttribute(pro);
		model.addAttribute("page", "product/add.jsp");
		
		List<Category> categories = categoryDao.getCategoriess();
		model.addAttribute("categories", categories);
		
		Category currentCat = pro.getCategory();
		model.addAttribute("currentCat", currentCat);
		
		return "admin/index";
	}
	
	@RequestMapping(value = "/admin/editProduct/{id}", method = RequestMethod.POST)
	public String saveUpdateProduct(Model model, @PathVariable long id, @ModelAttribute("product") Product product,
			@RequestParam("cat") Long catId, final RedirectAttributes redirectAttributes){
		
		Product pro = productDao.findProductById(id);
		pro.setName(product.getName());
		pro.setDescription(product.getDescription());
		pro.setCurrQty(product.getCurrQty());
		pro.setPrice(product.getPrice());
		
		Category proCat = categoryDao.findCategoryById(catId);
		pro.setCategory(proCat);
		productDao.createProduct(pro);
		
		redirectAttributes.addFlashAttribute("message","Product is updated successfully");
		return "redirect:/admin/product";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/admin/deleteProduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(Model model, @PathVariable long id, final RedirectAttributes redirectAttributes) {
		Product product = productDao.findProductById(id);
		
		redirectAttributes.addFlashAttribute("message", "Product is deleted successfully...");
		try{
		productDao.deleteProduct(product);
		}
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "User already ordered this product");
		}
		finally{
			return "redirect:/admin/product";
		}
	}
	
	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public String adminUser(Model model) {
		List<User> users= userDao.getUsers();
		model.addAttribute("users",users);
		model.addAttribute("page", "user/list.jsp");
		return "admin/index";
	}
	
	@RequestMapping(value = "/admin/order", method = RequestMethod.GET)
	public String adminOrder(Model model) {
		List<Order> orders= orderDao.getOrders();
		model.addAttribute("orders",orders);
		model.addAttribute("page", "order/list.jsp");
		return "admin/index";
	}
	
	@RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
	public String adminAddUser(Model model) {
		User user = new User();
		model.addAttribute(user);
		model.addAttribute("page","user/add.jsp");
		return "admin/index";
	}
	
	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public String saveAddUser(@ModelAttribute("user") User user, Model model,
			final RedirectAttributes redirectAttributes) {
		//System.out.println(category.getName());
		userDao.createUser(user);
		model.addAttribute("page", "user/list.jsp");
		redirectAttributes.addFlashAttribute("message", "User Added Successfully..");
		return "redirect:/admin/user";
	}
	
	@RequestMapping(value = "/admin/editUser/{id}", method = RequestMethod.GET)
	public String editAdminUser(Model model, @PathVariable long id) {
		
		User user = userDao.findUserById(id);
		model.addAttribute(user);
		model.addAttribute("page", "user/add.jsp");
		return "admin/index";
	}
	
	@RequestMapping(value = "/admin/editUser/{id}", method = RequestMethod.POST)
	public String updateAdminUser(Model model, @PathVariable long id, @ModelAttribute("user") User user, final RedirectAttributes redirectAttributes) {
		
		User usr = userDao.findUserById(id);
		usr.setUserName(user.getUserName());
		userDao.updateUser(usr);
		redirectAttributes.addFlashAttribute("message","User is updated successfully");
		
		return "redirect:/admin/user";
	}
	
	@RequestMapping(value = "/admin/deleteUser/{id}", method = RequestMethod.GET)
	public String deleteAdminUser(Model model, @PathVariable long id, final RedirectAttributes redirectAttributes) {
		
		User usr = userDao.findUserById(id);
		//cat.setCategoryId(id);
		userDao.deleteUser(usr);
		redirectAttributes.addFlashAttribute("message","User is deleted successfully");
		
		return "redirect:/admin/user";
	}

	private void initModelList(Model model) {
        Role[] rolesList =Role.class.getEnumConstants();
        model.addAttribute("roles", rolesList);
    }

}
