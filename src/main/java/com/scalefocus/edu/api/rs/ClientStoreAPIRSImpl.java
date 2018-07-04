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
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.scalefocus.edu.api.ClientStoreAPIRS;
import com.scalefocus.edu.api.model.AddressesAPI;
import com.scalefocus.edu.api.model.ClientsAPI;
import com.scalefocus.edu.service.ClientStoreService;



/* https://stackoverflow.com/questions/13594945/how-correctly-produce-json-by-restful-web-service */
/* http://users.cxf.apache.narkive.com/9PfDGlYi/cxf-jax-rs-client-sends-post-put-fine-get-delete-hang */
/* Java EE 8 Recipes A Problem-Solution Approach_2ed_p633 */

@Produces("application/json")
@Consumes({ "application/json" })
@Path("/")
@Controller
public class ClientStoreAPIRSImpl implements ClientStoreAPIRS {

	@Autowired
	private ClientStoreService clientStoreService;
	
	@Autowired
	private ClientStoreService addressStoreService;

	@GET
	@Path("/hi")
	public String sayHi() {
		System.out.println("RRRRRRRRRR");
		return clientStoreService.sayHi();
	}
	
	@GET
	@Path("/clients")
	@Produces("application/json")
	@Override
	public List<ClientsAPI> findAll() {
		System.out.println("@@@@@@@@@@@@@@");
		clientStoreService.findAll();
		return null;
	}
	
	@GET
	@Path("/clients/id/{id}")	
	@Produces("application/json")
	@Override
	public Response findById(@PathParam("id") int id) {	
		 System.out.println("***********");
		 System.out.println("id: " + id);
		 
		 clientStoreService.findById(id);
		return null;
	}
	
	@GET
	@Path("/clients/email/{email}")
	@Produces("application/json")
	@Override
	public Response findByEmail(@PathParam("email") String email) {
		System.out.println("##############");
		clientStoreService.findByEmail(email);
		return null;
	}	

	@POST
	@Path("/clients")
	@Consumes("application/json") 
	@Produces("application/json")
	@Override	
    public Response createClient(@RequestBody ClientsAPI client) { 
		System.out.println("..........");
		 clientStoreService.createClient(client);

        return null;
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
			clientStoreService.updateClient(id, clientsAPI);
		return  null;
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
		 clientStoreService.deleteClient(id);		
		return null;
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
	@Consumes("application/json") 
	@Produces("application/json")
	@Override	
	public Response createAddresses(@PathParam( "id" ) int id, AddressesAPI addressesAPI) {
		System.out.println("**********");
		addressStoreService.createAddresses(id, addressesAPI);
       return null;
	}
	

	@PUT
	@Path("/addresses/id/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Override
	public Response updateAddresses(@PathParam( "id" ) int id, AddressesAPI addressesAPI) {
		System.out.println("%%%%%%%%%%%%%%");
		addressStoreService.updateAddresses(id, addressesAPI);
		return  null;
	}
	
	@DELETE
	@Path("/addresses/id/{id}")		
	@Produces("application/json")
	@Override
	public Response deleteAddresses(@PathParam( "id" ) int id, AddressesAPI addressesAPI) {
		 System.out.println("&&&&&&&&&&&&&");
		 addressStoreService.deleteAddresses(id, addressesAPI);
		return null;
	}

	
	@GET
	@Path("/addresses/id/{id}")
	@Produces("application/json")
	@Override
	public List<AddressesAPI> showAll(@PathParam( "id" ) int id) {
		System.out.println("$$$$$$$$$$$$$");
		addressStoreService.showAll(id);
		return null;
	}
}
