package com.scalefocus.edu.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/* https://springframework.guru/category/java/page/2/ */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClientsAPI {
	
	private int id;
	@JsonProperty("email") private String email;
	@JsonProperty("firstName") private String firstName;
	@JsonProperty("lastName") private String lastName;
	@JsonProperty("addresses") private List<AddressesAPI> addresses;
	
	public ClientsAPI(String email, String firstName, String lastName, List<AddressesAPI> addresses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.addresses = addresses;
	}

	public ClientsAPI() {
		super();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
	
	public List<AddressesAPI> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressesAPI> addresses) {
		this.addresses = addresses;
	}
}

