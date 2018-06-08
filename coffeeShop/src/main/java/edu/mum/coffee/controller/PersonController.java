package edu.mum.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;

@Controller
@RequestMapping(value="/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String getAllPerson(Model model) {
		model.addAttribute("persons", personService.findAll());
		return "adminPersonList";
	}
	
	@RequestMapping(value="/changestatus/{id}")
	public String changeStatus(@PathVariable long id) {
		Person person=personService.findById(id);
		if(person.isEnable()) {
			person.setEnable(false);
		}else {
			person.setEnable(true);
		}
		personService.savePerson(person);
		return "redirect:/person/all";
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	
}
