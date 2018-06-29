package com.scalefocus.edu.db.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.sun.istack.NotNull;

/* http://www.javainterviewpoint.com/spring-restful-web-services-crud-example/ */
/* https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-two-crud/ */

	@Entity
	@Table(name = "clients")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NamedQuery(name="Clients.findAll", query="SELECT c FROM Clients c")
	@XmlRootElement()
	@XmlAccessorType(XmlAccessType.FIELD)
public class Clients implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id", unique = true)
    @PrimaryKeyJoinColumn(name = "client_id")
    private int id;

    @NotNull
    @Column(name = "email", length = 30, unique = true)	    
    private String email;

    @NotNull
    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;
    
    @OneToMany(mappedBy="clients", fetch = FetchType.EAGER )
	@Cascade(CascadeType.ALL)
	private List<Addresses> addresses;
    
    public Clients() {
    }

    public Clients(String email, String firstName, String lastName, List<Addresses> addresses) {			
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addresses = addresses;
	}

    public void setClientId(int id) {
		this.id = id;
	}
	
	public int getClientId() {
		return id;
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

    public List<Addresses> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Addresses> addresses) {			
		this.addresses = addresses;
	} 
}



