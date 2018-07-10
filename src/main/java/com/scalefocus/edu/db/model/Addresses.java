package com.scalefocus.edu.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

/* http://www.javainterviewpoint.com/spring-restful-web-services-crud-example/ */
/* https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-two-crud/ */

@Entity
@Table(name = "addresses")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NamedQuery(name="Addresses.findAll", query="SELECT c FROM Addresses c")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Addresses")	
public class Addresses implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
    private int id;

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
    
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="client_id")
	@Cascade(CascadeType.ALL)
    @ManyToOne(targetEntity = Clients.class)
    @JoinColumn(referencedColumnName = "client_id")
    @XmlTransient
    @JsonIgnore
    private Clients clients;
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public Clients getClient() {
		return clients;
	}

	public void setClient(Clients clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "Addresses [id=" + id + ", country=" + country + ", city=" + city + ", zipcode=" + zipcode
				+ ", addressline=" + addressline + ", clients=" + clients + "]";
	} 
	
}

