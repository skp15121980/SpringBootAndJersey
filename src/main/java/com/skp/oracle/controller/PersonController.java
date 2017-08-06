package com.skp.oracle.controller;

import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skp.oracle.domain.Person;
import com.skp.oracle.repository.PersonRepository;

@RestController
@RequestMapping("person")
public class PersonController {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PersonRepository repo;	
	
	// localhost:8080/person/82
	@RequestMapping(value="/{personId}", method = RequestMethod.GET)
	public Person getLineItem(@PathVariable Long personId) {
		log.info("controller getLineItem");
		return repo.getPerson(personId);
	}	
	@RequestMapping( method = RequestMethod.POST)
	public int createPerson(@RequestBody Person person) throws SQLException {
		log.info("controller getLineItem");
		ObjectMapper oMapper = new ObjectMapper();
		 Map<String, Object> map = oMapper.convertValue(person, Map.class);
		return repo.createPerson(map);
	}	
}
