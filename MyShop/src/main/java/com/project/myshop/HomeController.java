package com.project.myshop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.pojo.*;
import com.project.dao.OrderDao;
import com.project.dao.ProductDao;
import com.project.dao.UserDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private HttpSession httpsession;

	@Autowired
	@Qualifier("productDao")
	ProductDao productDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDao userDao;
	
	@Autowired
	@Qualifier("orderDao")
	OrderDao orderDao;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		List<Product> homeProduct = productDao.getProductsForHomePage();
		model.addAttribute("products", homeProduct);
		User user = new User();
		model.addAttribute("user", user);
		return "home";
	}

	@RequestMapping(value = "/showProduct/{id}", method = RequestMethod.GET)
	public String showProductDeatils(Model model, @PathVariable long id) {
		Product product = productDao.getProductDetails(id);
		model.addAttribute("product", product);
		User user = new User();
		model.addAttribute("user", user);
		return "productDetails";
	}

	@RequestMapping(value = "/removeCart/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String removeCart(@PathVariable("id") String productID) {
		JSONObject obj = new JSONObject();
		Long id = Long.parseLong(productID);
		if (httpsession.getAttribute("userId") == null || httpsession.getAttribute("userId") == "") {
			obj.put("login", 0);
		} else {
			obj.put("login", 1);
			List<Long> cart = (List<Long>) httpsession.getAttribute("products");
			if (cart.contains(id)) {
				cart.remove(id);
			}
			httpsession.setAttribute("products", cart);
			httpsession.setAttribute("total", cart.size());
			obj.put("products", cart);
		}

		return obj.toJSONString();
	}

	@RequestMapping(value = "/showProduct/addCart/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String addCart(@PathVariable("id") String productID) {

		JSONObject obj = new JSONObject();
		Long id = Long.parseLong(productID);
		if (httpsession.getAttribute("userId") == null || httpsession.getAttribute("userId") == "") {
			obj.put("login", 0);
		} else {
			obj.put("login", 1);
			List<Long> cart = (List<Long>) httpsession.getAttribute("products");
			if (!cart.contains(id)) {
				cart.add(id);
			}
			httpsession.setAttribute("products", cart);
			httpsession.setAttribute("total", cart.size());
			obj.put("products", cart);
		}

		return obj.toJSONString();
	}

	@RequestMapping(value = "**/login", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute User user, final RedirectAttributes redirectAttributes) {
		String username = user.getUserName();
		String password = user.getPassword();
		String view = "redirect:/";
		User validUser = userDao.findByUserNamePassword(username, password);
		if (validUser != null) {
			httpsession.setAttribute("userId", validUser.getId());
			httpsession.setAttribute("username", validUser.getUserName());
			httpsession.setAttribute("products", new ArrayList<Integer>());
			redirectAttributes.addFlashAttribute("message", "Login Successfull");
		} else {
			Administrator admin = userDao.findByAdminUserNamePassword(username, password);
			if (admin != null) {
				model.addAttribute("page", "adminHome");
				view = "admin/index";
			} else {
				redirectAttributes.addFlashAttribute("message", "Invalid Login");
			}
		}

		return view;
	}

	@RequestMapping(value = "**/logout", method = RequestMethod.GET)
	public String logout(Model model, final RedirectAttributes redirectAttributes) {
		// System.out.println(category.getName());
		httpsession.invalidate();
		redirectAttributes.addFlashAttribute("message", "Logout Successfully..");
		return "redirect:/";
	}

	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}

	@RequestMapping(value = "/**/showCart", method = RequestMethod.GET)
	public String productByCat(Model model) {
		List<Product> products = new ArrayList<Product>();
		List<Long> cart = (List<Long>) httpsession.getAttribute("products");
		for (Long id : cart) {
			products.add(productDao.findProductById(id));
		}

		model.addAttribute("products", products);

		User user = new User();
		model.addAttribute("user", user);
		return "cart";
	}

	@RequestMapping(value = "/processCart", method = RequestMethod.POST)
	public String processCart(Model model, HttpServletRequest request, HttpServletResponse response) {

		List<Product> products = new ArrayList<Product>();
		List<Long> cart = (List<Long>) httpsession.getAttribute("products");
		List<String> quantities = new ArrayList<String>();
		for (Long id : cart) {
			Product pr = productDao.findProductById(id);
			String quantity = request.getParameter("quantity[" + id + "]");
			quantities.add(quantity);
			pr.setName(pr.getName() + " X " + quantity);
			Double qu = Double.parseDouble(quantity);
			pr.setPrice(pr.getPrice() * qu);
			products.add(pr);

		}

		httpsession.setAttribute("quantity", quantities);

		double total = 0;
		for (Product pro : products) {
			total += pro.getPrice();
		}

		model.addAttribute("total", total);

		model.addAttribute("products", products);
		model.addAttribute("person", userDao.findUserById((Long) httpsession.getAttribute("userId")));
		User user = new User();
		model.addAttribute("user", user);
		return "billing";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkOut(Model model) {
		User usr1 = userDao.findUserById((Long) httpsession.getAttribute("userId"));
		Order ord1 = new Order(new Date(), 0, usr1, Status.PENDING);
		List<OrderLine> orderLines = new ArrayList<OrderLine>();

		List<Long> cart = (List<Long>) httpsession.getAttribute("products");
		List<String> quantities = (List<String>) httpsession.getAttribute("quantity");

		for (int i = 0; i < cart.size(); i++) {
			orderLines.add(
					new OrderLine(Integer.parseInt(quantities.get(i)), productDao.findProductById(cart.get(i)), ord1));
		}
		for (OrderLine ord : orderLines) {
			ord1.addOrderLines(ord);
		}

		orderDao.createOrder(ord1);
		for (OrderLine ord : orderLines) {
			orderDao.createOrderLine(ord);
		}

		User user = new User();
		model.addAttribute("user", user);
		httpsession.removeAttribute("products");
		httpsession.removeAttribute("total");
		httpsession.removeAttribute("quantity");
		httpsession.setAttribute("products", new ArrayList<Integer>());
		httpsession.setAttribute("quantity", new ArrayList<String>());
		return "success";
	}

}
