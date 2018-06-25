package com.scalefocus.edu.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.client.Client;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

/* http://www.javainterviewpoint.com/spring-restful-web-services-crud-example/ */
/* https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-two-crud/ */

	@Entity
	@Table(name = "addresses")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "Addresses")
	
public class Addresses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id", unique = true)
    private int addressId;

    @NotNull
    @Column(name = "country", length = 30)	    
    private String country;

    @NotNull
    @Column(name = "city", length = 30)
    private String city;

    @NotNull
    @Column(name = "zipcode", length = 10)
    private String zipcode;
    
    @NotNull
    @Column(name = "addressline", length = 100)
    private String addressline;
    
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name="client_id", nullable=false)
	@Cascade(CascadeType.ALL)
    private Client client;
    
    
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
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	} 
}

