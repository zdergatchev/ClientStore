package com.scalefocus.edu.api.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/* https://stackoverflow.com/questions/7105745/how-to-specify-jackson-to-only-use-fields-preferably-globally */
/* https://stackoverflow.com/questions/7105745/how-to-specify-jackson-to-only-use-fields-preferably-globally/22074210 */
/* https://springframework.guru/category/java/page/2/ */
/* After @JsonAutoDetect(fieldVisibility = Visibility.ANY) will serializes/deserialize to json the fields */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class AddressesAPI {

	private int addressId;
	@JsonProperty("country") private String country;
	@JsonProperty("city") private String city;
	@JsonProperty("zipcode") private String zipcode;
	@JsonProperty("addressline") private String addressline;

	public AddressesAPI() {
		super();
	}

	public AddressesAPI(String country, String city, String zipcode, String addressline) {
		super();
		this.country = country;
		this.city = city;
		this.zipcode = zipcode;
		this.addressline = addressline;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddressline() {
		return addressline;
	}

	public void setAddressline(String addressline) {
		this.addressline = addressline;
	}
}

