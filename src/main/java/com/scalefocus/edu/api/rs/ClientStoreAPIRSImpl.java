package com.scalefocus.edu.api.rs;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.scalefocus.edu.api.ClientStoreAPIRS;
import com.scalefocus.edu.api.model.ClientsAPI;
import com.scalefocus.edu.service.ClientStoreService;


/* https://stackoverflow.com/questions/13594945/how-correctly-produce-json-by-restful-web-service */
/* http://users.cxf.apache.narkive.com/9PfDGlYi/cxf-jax-rs-client-sends-post-put-fine-get-delete-hang */
/* Java EE 8 Recipes A Problem-Solution Approach_2ed_p633 */

@Produces("application/json")
@Consumes({ "application/json" })
@Path("/rs")
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
	@Path("/clients/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Override
//	public Response updateClient(@RequestBody int id, ClientsAPI clientsAPI) {
	public Response updateClient( @Context int id, ClientsAPI clientsAPI) {
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

	@GET
//	@Path("/clients/{param}")
	@Produces("application/json")
	@Override
	public List<ClientsAPI> findAll() {
		System.out.println("@@@@@@@@@@@@@@");
		clientStoreService.findAll();
		return null;
	}
	
//	public List<ClientsAPI> retrieveAllClients() {
//		return clientStoreService.findAll();
//	}

	@GET
	@Path("/{id}")	
	@Produces("application/json")
	@Override
//	public Response findById(@RequestBody int id) {	
	public Response findById(@PathParam( "id" ) int id) {	
		 System.out.println("***********");
		 clientStoreService.findById(id);
		return null;
	}

	@GET
	@Path("/{email}")
	@Produces("application/json")
	@Override
	//public Response findByEmail(@RequestBody String email) {	
	public Response findByEmail(@RequestParam(value = "email") String email) {
		System.out.println("##############");
		clientStoreService.findByEmail(email);
		return null;
	}	
	
}
