package edu.mum.coffee.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/addOrderline", method = RequestMethod.POST)
	public String addOrderline(HttpSession session, @ModelAttribute("orderline") Orderline orderline,
			RedirectAttributes redirectAttr) {
		Object orderObj = session.getAttribute("orderCart");
		if (orderObj == null) {
			orderObj = new Order();
			session.setAttribute("orderCart", orderObj);
		}
		Order order = (Order) orderObj;
		orderline.setOrder(order);
		order.addOrderLine(orderline);
		redirectAttr.addFlashAttribute("items", order.getOrderLines().size() + " item(s)");
		return "redirect:/product/menu";
	}

	@RequestMapping(value = "/orderDetails")
	public String checkOrder(HttpSession session, Model model) {
		Object orderObj = session.getAttribute("orderCart");
		if (orderObj == null) {
			return "redirect:/product/menu";
		}
		Order order = (Order) orderObj;
		String message = "Total quantity ordered: " + order.getQuantity() + " / Total amount: $"
				+ order.getTotalAmount();
		model.addAttribute("order", order);
		model.addAttribute("message", message);
		return "customerOrderDetails";
	}

	@RequestMapping(value = "/validateOrder")
	public String validateOrder(HttpSession session, Model model, RedirectAttributes redirectAttr,
			Authentication authentication) {
		Object orderObj = session.getAttribute("orderCart");
		Order order = (Order) orderObj;
		order.setOrderDate(new Date());
		Person person = personService.findById(new Long(2));
		order.setPerson(person);
		orderService.save(order);
		String message = "You have successfully made your order! Total quantity ordered: " + order.getQuantity()
				+ " / Total amount: $" + order.getTotalAmount();
		session.removeAttribute("orderCart");
		redirectAttr.addFlashAttribute("message", message);
		return "redirect:/product/menu";
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
