package com.scalefocus.edu.api;

import java.util.List;

import com.scalefocus.edu.api.model.ClientsAPI;
import com.scalefocus.edu.db.model.Clients;

public interface ClientStoreAPI {
	public void createClient();									// POST
	public void updateClient(int id);							// PUT	
	public void deleteClient(int id);							// DELETE
	public ClientsAPI retrieveClientsById(int id);  			// GET
	public ClientsAPI retrieveClientsByEmail(String email);  	// GET
	public List<ClientsAPI> retrieveAllClients();				// GET
							  					
	
}
