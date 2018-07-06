package com.scalefocus.edu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scalefocus.edu.api.model.AddressesAPI;
import com.scalefocus.edu.api.model.ClientsAPI;
import com.scalefocus.edu.db.dao.AddressesDao;
import com.scalefocus.edu.db.dao.ClientsDao;
import com.scalefocus.edu.db.model.Addresses;
import com.scalefocus.edu.db.model.Clients;

@Service
public class ClientStoreService {

	@Autowired
	private ClientsDao clientsDao;
	
	@Autowired
	private AddressesDao addressesDao;

	@Autowired
	private DozerBeanMapper mapper;

	public Clients createClient(ClientsAPI clientsAPI) {
//		String email = clientsAPI.executeQuery("SELECT email FROM clients");
//		if (clientsDao.exists(clientsAPI.getEmail())) {		
//		}
		if(this.clientsDao.findByEmail(clientsAPI.getEmail()) == null) {
			this.clientsDao.save(mapApiToClient(clientsAPI));
			System.out.println("Client is created");
		} else {
			 System.out.println("A Client with the same data already exist");
		}
			
		return null;
	}

	public Clients findById(int id) {
		Clients clients = this.clientsDao.findById(id);
//		if (this.clientsDao.findById(id) != null) {
		if (clients != null) {
//			System.out.println(this.clientsDao.findById(id));
			System.out.println(clients);
		} else {
			System.out.println("Client not found by id");
		}
		return null;
	}
	
	public Clients findByEmail(String email) {
		
		Clients clients = this.clientsDao.findByEmail(email);
//		System.out.println(email);
		if (clients != null) {
//			System.out.println(clients.getEmail());
			System.out.println(clients);
		} else {
			System.out.println("Client not found by email");
		}
		return null;
	}

	// https://stackoverflow.com/questions/49224010/i-cant-use-findone-method-in-my-code

//	public Clients retrieveClientsByEmail(String email) {
//		// Clients client = this.clientsDao.findOne(email);
//		// return client;
//		Clients client = this.clientsDao.retrieveClientsByEmail(email);
//		return client;
//	}
	
/* https://stackoverflow.com/questions/9947221/how-to-print-out-individual-strings-from-iterablestring */
	public List<ClientsAPI> findAll() {
		Iterable<Clients> dbClients = this.clientsDao.findAll();
		for(Clients c : dbClients){
			System.out.println(c.getClientId() + " " + c.getEmail() + " " + c.getFirstName() + " " + c.getLastName());
		}
//		dbClients.forEach(dbClient-> System.out.println(dbClient));
		return null;		
	 }
	
	public Clients deleteClient(int id) {
		if (this.clientsDao.findById(id) != null) {
			this.clientsDao.delete(id);
			System.out.println("Client is deleted successful");
		} else {
			 System.out.println("A Client is not delete");
		}
		return null;
//	return new Clients();
	}
	
	
	public Clients updateClient(int id, ClientsAPI clientsAPI) {
		Clients clients = this.clientsDao.findById(id);
//		if(this.clientsDao.findOne(clientsAPI.getId()) != null) {	
		if (clients != null) {			
			if(clients.getFirstName() != clientsAPI.getFirstName() ) {
				clients.setFirstName(clientsAPI.getFirstName());
			}
			
			if(clients.getLastName() != clientsAPI.getLastName() ) {
				clients.setLastName(clientsAPI.getLastName());
			}
			

//			List<Addresses> dbAddresses = new ArrayList<Addresses>();
//			List<AddressesAPI> apiAddresses = new ArrayList<AddressesAPI>();
//			apiAddresses.add(clients.getAddresses());//

			
//			apiAddresses.add("Bulgaria");
//			apiAddresses.add("Sofia");
//			apiAddresses.add("1000");
//			apiAddresses.add("Mogilata Str. 56");
//			Addresses adress1 = new Addresses();
//			Addresses adress2 = new Addresses();
//			Addresses adress3 = new Addresses();
//			
//			adress1.setCountry("Bulgaria");
//			adress1.setCity("Sofia");
//			adress1.setZipcode("1000");
//			adress1.setAddressline("Mogilata Str. 56");
//			adress1.setClient(clients);
//			
//			adress2.setCountry("Bulgaria");
//			adress2.setCity("Sliven");
//			adress2.setZipcode("8800");
//			adress2.setAddressline("Sirma Str. 10");
//			adress2.setClient(clients);
//			
//			adress3.setCountry("Bulgaria");
//			adress3.setCity("Plovdiv");
//			adress3.setZipcode("4000");
//			adress3.setAddressline("Tzar Boris Bul. 128");
//			adress3.setClient(clients);
//			
//			dbAddresses.add(adress1);
//			dbAddresses.add(adress2);
//			dbAddresses.add(adress3);
//			
//			clients.setAddresses(dbAddresses);
			
//			for(Addresses addresses : client.getAddresses()) {
//			this.addressesDao.delete(addresses.getAddressId());
		//}
		//clients = this.addressesDao.save(clients);
		
			
			this.clientsDao.save(clients);

			System.out.println("Client is updated");
		}
		
		return null;
	}	
			


	public String sayHi() {
		return "Hi!";
	}

	private Clients mapApiToClient(ClientsAPI clientsAPI) {
		return mapper.map(clientsAPI, Clients.class);
	}
	
// ADDRESES
	
	public Addresses createAddresses(int id, AddressesAPI addressesAPI){
		Clients clients = this.clientsDao.findById(id);
		
		List<Addresses> dbAddresses = new ArrayList<Addresses>();
		
		if (clients.getAddresses().size() == 0) {
			System.out.println("no addresses");
			Addresses adr = mapper.map(addressesAPI, Addresses.class);
			adr.setClient(clients);
//			adr.setCountry(addressesAPI.getCountry());
//			adr.setCity(addressesAPI.getCity());
//			adr.setZipcode(addressesAPI.getZipcode());
//			adr.setAddressline(addressesAPI.getAddressline());
			System.out.println("id---------: " + adr.getId());
			dbAddresses.add(adr);
			clients.getAddresses().add(adr);
			
		}	else {
			System.out.println("has addresses");
			dbAddresses = clients.getAddresses();
			
			for (Addresses add : dbAddresses) {
				if (add.getCity() != addressesAPI.getCity() && 	add.getCountry() != addressesAPI.getCountry() &&
						add.getZipcode() != addressesAPI.getZipcode() && add.getAddressline() != addressesAPI.getAddressline()) {
					Addresses adr = new Addresses();	
					adr.setClient(clients);
					adr.setCountry(addressesAPI.getCountry());
					adr.setCity(addressesAPI.getCity());
					adr.setZipcode(addressesAPI.getZipcode());
					adr.setAddressline(addressesAPI.getAddressline());
					dbAddresses.add(adr);
					clients.setAddresses(dbAddresses);
				}
			}
		}	

//		clients.setAddresses(dbAddresses);
//				this.addressesDao.save(addresses);
//				this.clientsDao.save(clients);
		System.out.println(dbAddresses.size());
		System.out.println(clients.getAddresses());

		this.clientsDao.save(clients);
				
	   return null;

	}
	
	public Addresses updateAddresses(int id, AddressesAPI addressesAPI){		
//		Clients clients = this.clientsDao.findById(id);
		Addresses address = this.addressesDao.findById(id);

		
		if (address != null) {				
			address.setCountry(addressesAPI.getCountry());
			address.setCity(addressesAPI.getCity());
			address.setZipcode(addressesAPI.getZipcode());
			address.setAddressline(addressesAPI.getAddressline());
		}			

		this.addressesDao.save(address);

		System.out.println(this.addressesDao.save(address));
		
		return null;
	}
	
	public Addresses deleteAddresses(int id, AddressesAPI addressesAPI){
		Addresses address = this.addressesDao.findById(id);

			if (address != null) {
	
				this.addressesDao.delete(address);

			}	

		return null;
	}
	
	public Addresses editAddresses(int id, AddressesAPI addressesAPI){
		Addresses address = this.addressesDao.findById(id);
		
		if (address != null) {				
			address.setCountry(addressesAPI.getCountry());
			address.setCity(addressesAPI.getCity());
			address.setZipcode(addressesAPI.getZipcode());
			address.setAddressline(addressesAPI.getAddressline());
		}			

		this.addressesDao.save(address);

		System.out.println(this.addressesDao.save(address));
		
		return null;
	}
	
	public List<AddressesAPI> showAll(int id) {
		Clients clients = this.clientsDao.findById(id);
		
		List<Addresses> dbAddresses = clients.getAddresses();
		for(Addresses a : dbAddresses){
			System.out.println(a.getId() + " " + a.getCountry() + " " + a.getCity() + " " + a.getZipcode());
		}
//		dbClients.forEach(dbClient-> System.out.println(dbClient));
 		return null;	
	}	
	
}















