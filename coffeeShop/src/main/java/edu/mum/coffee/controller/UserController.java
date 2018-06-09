package edu.mum.coffee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.coffee.domain.User;
import edu.mum.coffee.domain.UserRole;
import edu.mum.coffee.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String getAdminAddUserForm(@ModelAttribute User user) {
		return "adminNewPersonForm";
	}
	
	@RequestMapping(value="/userSignUpForm", method=RequestMethod.GET)
	public String getUserForm(@ModelAttribute User user) {
		user.setRole(UserRole.CUSTOMER);
		return "signupForm";
	}
	
	@RequestMapping(value="/userDetails", method=RequestMethod.GET)
	public String getDetailsForm(Model model) {
		model.addAttribute("user", userService.findByUsername("gakyvan"));
		return "customerDetails";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String createUser(@ModelAttribute @Valid User user, BindingResult result, RedirectAttributes redirect) {
		if(!result.hasErrors()) {
			user.getPerson().setEnable(true);
			userService.saveUser(user);
			redirect.addFlashAttribute("message", "The Person was successfully saved");
			return "redirect:/person/all";
		}
		return "adminNewPersonForm";
	}
	
	@RequestMapping(value="/createProfile", method=RequestMethod.POST)
	public String createCustomer(@ModelAttribute @Valid User user, BindingResult result, RedirectAttributes redirect) {
		if(!result.hasErrors()) {
			user.getPerson().setEnable(true);
			userService.saveUser(user);
			redirect.addFlashAttribute("message", "The Person was successfully saved");
			return "redirect:/user/userSignUpForm";
		}
		return "signupForm";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editUser(@ModelAttribute @Valid User user, BindingResult result, RedirectAttributes redirect) {
		if(!result.hasErrors()) {
			user.getPerson().setEnable(true);
			userService.saveUser(user);
			redirect.addFlashAttribute("message", "Your profile was successfully updated.");
			return "redirect:/user/userDetails";
		}
		return "customerDetails";
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
}
