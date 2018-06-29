package com.scalefocus.edu.api;

import java.util.List;

import javax.ws.rs.core.Response;

import com.scalefocus.edu.api.model.ClientsAPI;

public interface ClientStoreAPIRS {
	Response createClient(ClientsAPI clients);								// POST
	Response updateClient(int id, ClientsAPI clientsAPI);					// PUT	
	Response deleteClient(int id);											// DELETE
	public Response findById(int id);  										// GET
	public Response findByEmail(String email);  							// GET
	public List<ClientsAPI> findAll();										// GET
	public String sayHi();						  					
	
}
