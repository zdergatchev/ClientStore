package com.scalefocus.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Clients clients = this.clientsDao.findByEmail(clientsAPI.getEmail());
//		if(this.clientsDao.findByEmail(clientsAPI.getEmail()) == null) {
		if(clients == null) {
			this.clientsDao.save(mapApiToClient(clientsAPI));
			System.out.println("Client is created");
		} else {
			 System.out.println("A Client with the same data already exist");
		}
		
		return clients;
//		return null;
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
//		return null;
		return clients;
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
//		return null;
		return clients;
	}

	// https://stackoverflow.com/questions/49224010/i-cant-use-findone-method-in-my-code

//	public Clients retrieveClientsByEmail(String email) {
//		// Clients client = this.clientsDao.findOne(email);
//		// return client;
//		Clients client = this.clientsDao.retrieveClientsByEmail(email);
//		return client;
//	}
	
/* https://stackoverflow.com/questions/9947221/how-to-print-out-individual-strings-from-iterablestring */
/* https://stackoverflow.com/questions/1358595/how-to-map-collections-in-dozer */
	public List<ClientsAPI> findAll() {
		List<Clients> dbClients = this.clientsDao.findAll();
		List<ClientsAPI> clients = new ArrayList<ClientsAPI>();
		
		for(Clients db : dbClients) {
			clients.add(mapper.map(db, ClientsAPI.class));
		}	
		return clients;		
	 }
	
	public Clients deleteClient(int id) {
//		Clients clients = this.clientsDao.findById(id);
		if (this.clientsDao.findById(id) != null) {
//		if (clients != null) {
				this.clientsDao.delete(id);
			System.out.println("Client is deleted successful");
		} else {
			 System.out.println("A Client is not delete");
		}
//		return clients;
		return null;
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
		
//			this.clientsDao.save(clients);
			clients = this.clientsDao.save(clients);

			System.out.println("Client is updated");
		}
		
		return clients;
//		return null;
	}	
			


	public String sayHi() {
		return "Hi!";
	}
	
// MAPPERS	

	private Clients mapApiToClient(ClientsAPI clientsAPI) {
		return mapper.map(clientsAPI, Clients.class);
	}
	

	
// ADDRESES
	
	
	public Addresses createAddresses(int id, AddressesAPI addressesAPI){
		
		Clients clients = this.clientsDao.findById(id);
		
		List<Addresses> dbAddresses = null;
		Addresses adr = null;
		
		if(clients != null) {	
			if (clients.getAddresses().size() == 0) {
				dbAddresses = new ArrayList<Addresses>();
				System.out.println("no addresses");
				adr = mapper.map(addressesAPI, Addresses.class);
				adr.setClient(clients);
				System.out.println("id---------: " + adr.getId());
				dbAddresses.add(adr);
				clients.getAddresses().add(adr);
				
			}	else {			
				System.out.println("has addresses");
				dbAddresses = clients.getAddresses();
				
				for (Addresses add : dbAddresses) {
					
					if (add.getCity() != addressesAPI.getCity() && 	add.getCountry() != addressesAPI.getCountry() &&
							add.getZipcode() != addressesAPI.getZipcode() && add.getAddressline() != addressesAPI.getAddressline()) {
						adr = new Addresses();
						adr.setClient(clients);
						adr.setCountry(addressesAPI.getCountry());
						adr.setCity(addressesAPI.getCity());
						adr.setZipcode(addressesAPI.getZipcode());
						adr.setAddressline(addressesAPI.getAddressline());
						//clients.getAddresses().add(adr);
						System.out.println("1");
					}
				}
			}
			System.out.println(adr);
			this.addressesDao.save(adr);
			System.out.println("asda");
			
		} else {
			
			System.out.println("There is no such a client");			
		}

		
		return adr;

	}
	
	public Addresses updateAddresses(int id, AddressesAPI addressesAPI){		
//		Clients clients = this.clientsDao.findById(id);
		Addresses address = this.addressesDao.findById(id); 

		
		if (address != null) {				
			address.setCountry(addressesAPI.getCountry());
			address.setCity(addressesAPI.getCity());
			address.setZipcode(addressesAPI.getZipcode());
			address.setAddressline(addressesAPI.getAddressline());
		} else {
			System.out.println("There is no such an addres");	
		}			

		this.addressesDao.save(address);

		System.out.println(this.addressesDao.save(address));
		
		return address;
	}
	
	public Addresses deleteAddresses(int id, AddressesAPI addressesAPI){
		Addresses address = this.addressesDao.findById(id);
//		Addresses result = null;
			if (address != null) {
//				result = new Addresses();
				this.addressesDao.delete(id);
				System.out.println("Deleted successful " + address);

			} else {
				System.out.println("There is no such an addres");	
			}

//		return address;
		return null;
	}
	
	public Addresses editAddresses(int id, AddressesAPI addressesAPI){
		Addresses address = this.addressesDao.findById(id);
		
		if (address != null) {				
			address.setCountry(addressesAPI.getCountry());
			address.setCity(addressesAPI.getCity());
			address.setZipcode(addressesAPI.getZipcode());
			address.setAddressline(addressesAPI.getAddressline());
		} else {
			System.out.println("There is no such an addres");	
		}			

		this.addressesDao.save(address);

		System.out.println(this.addressesDao.save(address));
		
		return address;
	}
	
	public List<AddressesAPI> showAll(int id) {
		Clients clients = this.clientsDao.findById(id);
		
		List<Addresses> dbAddresses = clients.getAddresses();
		List<AddressesAPI> address = new ArrayList<AddressesAPI>();			

		for(Addresses db : dbAddresses) {
			address.add(mapper.map(db, AddressesAPI.class));
		}
		return address;	
	}		
}
































//for(Addresses a : dbAddresses){
//System.out.println(a.getId() + " " + a.getCountry() + " " + a.getCity() + " " + a.getZipcode());
//}
////dbClients.forEach(dbClient-> System.out.println(dbClient));
//return null;
//for(Addresses db :dbAddresses){
//((AddressesAPI) address).setId(mapper.map(db, AddressesAPI.class).getId());
//((AddressesAPI) address).setCountry(mapper.map(db, AddressesAPI.class).getCountry());
//((AddressesAPI) address).setCity(mapper.map(db, AddressesAPI.class).getCity());
//((AddressesAPI) address).setAddressline(mapper.map(db, AddressesAPI.class).getAddressline());
//}
//



//for(Addresses db : dbAddresses) {
//	((List<AddressesAPI>) address).add(mapper.map(db, AddressesAPI.class));
//}




//((ClientsAPI) clients).set(mapper.map(dbClients, ClientsAPI.class));
//for(Clients db : dbClients){
//	((ClientsAPI) clients).setId(mapper.map(db, ClientsAPI.class).getId());
//	((ClientsAPI) clients).setEmail(mapper.map(db, ClientsAPI.class).getEmail());
//	((ClientsAPI) clients).setFirstName(mapper.map(db, ClientsAPI.class).getFirstName());
//	((ClientsAPI) clients).setLastName(mapper.map(db, ClientsAPI.class).getLastName());
//}






//for(Clients c : dbClients){
//System.out.println(c.getClientId() + " " + c.getEmail() + " " + c.getFirstName() + " " + c.getLastName());
//}
//dbClients.forEach(dbClient-> System.out.println(dbClient));

//clients.add(mapper.map(clients, ClientsAPI.class).getEmail());
//for((ClientsAPI) c : dbClients){
//System.out.println(c.getClientId() + " " + c.getEmail() + " " + c.getFirstName() + " " + c.getLastName());
//}


