package com.scalefocus.edu.api.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.scalefocus.edu.api.model.ClientsAPI;
import com.scalefocus.edu.db.model.Clients;
import com.scalefocus.edu.service.ClientStoreService;

@Controller
@WebService(targetNamespace = "http://localhost:8080/ClientStore/ws/clients?wsdl")
public class ClientStoreAPIWSImpl {
	//public class ClientStoreAPIWSImpl implements ClientStoreAPIWS {

	@Autowired
	private ClientStoreService clientStoreService;
	
	@WebMethod
	@WebResult(name="clients")
	public List<ClientsAPI> findAll() {
		System.out.println("----------");
		List<ClientsAPI> allClients = clientStoreService.findAll();		
		//System.out.println(allClients);
		//return allClients;
		return allClients;
	}	
	
	public Clients createClient(@WebParam ClientsAPI client) { 
		System.out.println("..........");
		Clients clients = clientStoreService.createClient(client);
		return clients;
	 }
	

	public Clients findById(@WebParam(name = "id") int id) {	
		 System.out.println("***********");
		 System.out.println("id: " + id);		 
		 Clients clients = clientStoreService.findById(id);
		 return clients;
	}
	
	public Clients findByEmail(@WebParam(name = "email") String email) {
		System.out.println("##############");
		Clients clients = clientStoreService.findByEmail(email);
//		return Response.status(200).entity(client).type(MediaType.APPLICATION_XML).build();
		return clients;
	}


	public Clients updateClient(@WebParam(name = "id") int id, ClientsAPI clientsAPI) {
		System.out.println("^^^^^^^^^^^^^^");
		Clients clients = clientStoreService.updateClient(id, clientsAPI);
//		return Response.status(200).entity(client).type(MediaType.APPLICATION_XML).build();
		return clients;
	}
	
	public Clients deleteClient(@WebParam(name = "id") int id) {	
		 System.out.println("..........");
		 Clients clients = clientStoreService.deleteClient(id);	
//		 return Response.status(200).entity(clients).type(MediaType.APPLICATION_XML).build();
		 return clients;
	}
	
	@WebMethod
	public String sayHi() {
		return "Hi";
	}
}


