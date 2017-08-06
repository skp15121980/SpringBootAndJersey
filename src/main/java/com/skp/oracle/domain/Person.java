package com.skp.oracle.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("child")
	private Map<String,String> child = new HashMap<String, String>();
	public Person() {
		
	}

	public Person(Long id, String firstName, String lastName, String title, Map<String, String> child) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.child = child;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonAnyGetter
	public Map<String, String> getChild() {
		return child;
	}
	@JsonAnyGetter
	public void setChild(Map<String, String> child) {
		/*if(child instanceof Map){
            this.child.putAll((Map<String, String>) child);
        }*/
		this.child=child;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title
				+ ", child=" + child + "]";
	}

}
