package edu.mum.coffee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.coffee.domain.User;
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
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
}
