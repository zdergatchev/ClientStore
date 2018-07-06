package com.scalefocus.edu.api;

import java.util.List;

import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;
import com.scalefocus.edu.api.model.AddressesAPI;
import com.scalefocus.edu.api.model.ClientsAPI;

public interface ClientStoreAPIRS {
	Response createClient(ClientsAPI clients);								// POST
	Response updateClient(int id, ClientsAPI clientsAPI);					// PUT	
	Response deleteClient(int id);											// DELETE
	public Response findById(int id);  										// GET
	public Response findByEmail(String email);  							// GET
	public List<ClientsAPI> findAll();										// GET
	public String sayHi();	
	
	Response createAddresses(int id, AddressesAPI addressesAPI);			// POST
	Response updateAddresses(int id, AddressesAPI addressesAPI);			// PUT
	Response deleteAddresses(int id, AddressesAPI addressesAPI);			// DELETE
	Response editAddresses(int id, AddressesAPI addressesAPI);				// PUT
	List<AddressesAPI> showAll(int id);	
//	List<JsonObject> showAll(int id);	
}
