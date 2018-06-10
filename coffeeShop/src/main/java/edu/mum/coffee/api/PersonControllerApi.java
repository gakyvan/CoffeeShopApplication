package edu.mum.coffee.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;

@RestController
public class PersonControllerApi {

	@Autowired
	private PersonService personService;

	@CrossOrigin
	@RequestMapping(value = "/api/persons", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllPersons() {
		List<Person> persons = personService.findAll();
		if (persons.isEmpty()) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPerson(@PathVariable("id") long id) {
		Person person = personService.findById(id);
		if (person == null) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/person", method = RequestMethod.POST)
	public ResponseEntity<?> createPerson(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
		personService.savePerson(person);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/person/{id}").buildAndExpand(person.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/person/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
		Person pers = personService.findById(id);
		if (pers == null) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		pers.setAddress(person.getAddress());
		pers.setEmail(person.getEmail());
		pers.setEnable(person.isEnable());
		pers.setFirstName(person.getFirstName());
		pers.setLastName(person.getLastName());
		pers.setPhone(person.getPhone());
		personService.savePerson(pers);
		return new ResponseEntity<Person>(pers, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/person/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removePerson(@PathVariable("id") long id) {
		Person person = personService.findById(id);
		if (person == null) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		personService.removePerson(person);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	 
}
