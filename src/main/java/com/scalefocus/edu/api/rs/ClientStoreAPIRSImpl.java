package com.scalefocus.edu.api.rs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.scalefocus.edu.api.ClientStoreAPIRS;
import com.scalefocus.edu.api.model.AddressesAPI;
import com.scalefocus.edu.api.model.ClientsAPI;
import com.scalefocus.edu.db.model.Addresses;
import com.scalefocus.edu.db.model.Clients;
import com.scalefocus.edu.service.ClientStoreService;



/* https://stackoverflow.com/questions/13594945/how-correctly-produce-json-by-restful-web-service */
/* http://users.cxf.apache.narkive.com/9PfDGlYi/cxf-jax-rs-client-sends-post-put-fine-get-delete-hang */
/* Java EE 8 Recipes A Problem-Solution Approach_2ed_p633 */

@Produces("application/json")
@Consumes({ "application/json" })
//@Path("/")
@Controller
public class ClientStoreAPIRSImpl implements ClientStoreAPIRS {

	@Autowired
	private ClientStoreService clientStoreService;

	
	@GET
	@Path("/hi")
	public String sayHi() {
		System.out.println("RRRRRRRRRR");
		return clientStoreService.sayHi();
	}
	
/* https://stackoverflow.com/questions/8165908/return-a-list-of-objects-when-using-jax-rs */	
/* https://dzone.com/articles/jax-rs-list-generic-type-erasure */
	@GET
	@Path("/clients")
	@Override
	public Response findAll() {
		System.out.println("@@@@@@@@@@@@@@");
		List<ClientsAPI> allClients = clientStoreService.findAll();		
		System.out.println(allClients);
		return Response.status(200).entity(allClients).type(MediaType.APPLICATION_JSON).build();
//		return null;
	}
	
	@GET
	@Path("/clients/id/{id}")	
	@Override
	public Response findById(@PathParam("id") int id) {	
		 System.out.println("***********");
		 System.out.println("id: " + id);		 
		 Clients client = clientStoreService.findById(id);
		 return Response.status(200).entity(client).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/clients/email/{email}")
	@Produces("application/json")
	@Override
	public Response findByEmail(@PathParam("email") String email) {
		System.out.println("##############");
		Clients client = clientStoreService.findByEmail(email);
		return Response.status(200).entity(client).type(MediaType.APPLICATION_JSON).build();
//		return null;
	}	

	@POST
	@Path("/clients")
	@Consumes("application/json") 
	@Produces("application/json")
	@Override	
    public Response createClient(@RequestBody ClientsAPI client) { 
		System.out.println("..........");
		Clients clients = clientStoreService.createClient(client);
		return Response.status(200).entity(clients).type(MediaType.APPLICATION_JSON).build();
//      return null;
    }	


	@PUT
	@Path("/clients/id/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Override
//	public Response updateClient(@RequestBody int id, ClientsAPI clientsAPI) {
//	public Response updateClient( @Context int id, ClientsAPI clientsAPI) {
	public Response updateClient( @PathParam( "id" ) int id, ClientsAPI clientsAPI) {
		System.out.println("^^^^^^^^^^^^^^");
		Clients client = clientStoreService.updateClient(id, clientsAPI);
		return Response.status(200).entity(client).type(MediaType.APPLICATION_JSON).build();
//		return  null;
	}

	@DELETE
	@Path("/clients/{id}")		
	@Produces("application/json")
	@Override
/* https://jaejeongk.blogspot.com/2013/06/developerworks-constructing-rest.html */	
//	public Response deleteClient(@RequestParam("id") int id) {	
//	public Response deleteClient(@RequestBody int id) {	
	public Response deleteClient(@PathParam( "id" ) int id) {	
		 System.out.println("..........");
		 Clients clients = clientStoreService.deleteClient(id);	
		 return Response.status(200).entity(clients).type(MediaType.APPLICATION_JSON).build();
//		 return null;
	}

	
// ADDRESSES
	
//	@GET
//	@Path("/addresses/hi")
//	public String sayHi() {
//		System.out.println("RRRRRRRRRR");
//		return "hi";
//	}
	
	@POST
	@Path("/addresses/id/{id}")
	@Override	
	public Response createAddresses(@PathParam( "id" ) int id, AddressesAPI addressesAPI) {		
		Addresses address = clientStoreService.createAddresses(id, addressesAPI);
		return Response.status(200).entity(address).type(MediaType.APPLICATION_JSON).build();
//		return Response.status(200).entity(address).build();
//		return Response.status(200).entity(clientStoreService.findById(id).getAddresses()).type("application/json").build();
//		return null;
	}
	

	@PUT
	@Path("/addresses/id/{id}")
	@Consumes("application/json")
	@Produces("application/json")	
	@Override
	public Response updateAddresses(@PathParam( "id" ) int id, AddressesAPI addressesAPI) {
		System.out.println("%%%%%%%%%%%%%%");
		Addresses address = clientStoreService.updateAddresses(id, addressesAPI);
		return Response.status(200).entity(address).type(MediaType.APPLICATION_JSON).build();
//		return Response.status(200).entity(address).type("application/json").build();
//		return Response.status(201).build();
		//return  null;
	}
	
	@PUT
	@Path("/addresses/id/{id}/id/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Override
	public Response editAddresses(@PathParam( "id" ) int id, AddressesAPI addressesAPI) {
		System.out.println("~~~~~~~~~~~~~~~");
		Addresses address = clientStoreService.editAddresses(id, addressesAPI);
		return Response.status(200).entity(address).type(MediaType.APPLICATION_JSON).build();
//		return Response.status(201).build();
		//return  null;
	}
	
	@DELETE
	@Path("/addresses/id/{id}")		
	@Produces("application/json")
	@Override
	public Response deleteAddresses(@PathParam( "id" ) int id, AddressesAPI addressesAPI) {
		 System.out.println("&&&&&&&&&&&&&");	
		 Addresses address = clientStoreService.deleteAddresses(id, addressesAPI);
		 System.out.println(address);
		 return Response.status(200).entity(address).type(MediaType.APPLICATION_JSON).build();
//		 return Response.status(201).build();
		//return null;
	}

	
	@GET
	@Path("/addresses/id/{id}")
	@Produces("application/json")
	@Override
	public Response showAll(@PathParam( "id" ) int id) {
		System.out.println("$$$$$$$$$$$$$");
		List<AddressesAPI> allAddresses = clientStoreService.showAll(id);
		return Response.status(200).entity(allAddresses).type(MediaType.APPLICATION_JSON).build();
//		clientStoreService.showAll(id);
//		return null;		
	}
}
