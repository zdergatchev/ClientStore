package com.scalefocus.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		if (this.clientsDao.findById(id) != null) {
			System.out.println(this.clientsDao.findById(id));
		} else {
			System.out.println("Client not found by id");
		}
		return null;
	}
	
	public Clients findByEmail(String email) {
		if (this.clientsDao.findByEmail(email) != null) {
			System.out.println(this.clientsDao.findByEmail(email));
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
			System.out.println(c.getEmail() + c.getFirstName() + c.getLastName());
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
		Clients dbClient = this.clientsDao.findOne(id);
//		if(this.clientsDao.findOne(clientsAPI.getId()) != null) {			
			if (dbClient != null) {
			clientsAPI.setId(id);
			
			for(Addresses addresses : dbClient.getAddresses()) {
				this.addressesDao.delete(addresses.getAddressId());
			}
			dbClient = this.addressesDao.save(mapApiToClient(clientsAPI));
			System.out.println(dbClient);
		}
		return null;
	}	


	public String sayHi() {
		return "Hi!";
	}

	private Clients mapApiToClient(ClientsAPI clientsAPI) {
		return mapper.map(clientsAPI, Clients.class);
	}

}
