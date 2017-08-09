package com.skp.oracle.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Person {

	@JsonProperty("taskId")
	private UUID taskId;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("title")
	private String title;
	
	  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonProperty("dob")
	private Date dob;
	
	@JsonProperty("child")
	private Map<String,String> child = new HashMap<String, String>();
	public Person() {
		
	}

	public Person(UUID taskId, String firstName, String lastName, String title, Date dob) {
		super();
		this.taskId = taskId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.dob = dob;
	}


	public UUID getTaskId() {
		return taskId;
	}


	public void setTaskId(UUID taskId) {
		this.taskId = taskId;
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

	//@JsonAnyGetter
	public Map<String, String> getChild() {
		return child;
	}
	//@JsonAnyGetter
	public void setChild(Map<String, String> child) {
		/*if(child instanceof Map){
            this.child.putAll((Map<String, String>) child);
        }*/
		this.child=child;
	}
	/*@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title
				+ ", child=" + child + "]";
	}*/

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
