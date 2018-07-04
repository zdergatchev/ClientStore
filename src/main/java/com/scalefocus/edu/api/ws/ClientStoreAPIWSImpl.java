package com.scalefocus.edu.api.ws;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.scalefocus.edu.api.ClientStoreAPIWS;
import com.scalefocus.edu.api.model.AddressesAPI;
import com.scalefocus.edu.api.model.ClientsAPI;
import com.scalefocus.edu.service.ClientStoreService;

@Controller
@WebService
 public class ClientStoreAPIWSImpl implements ClientStoreAPIWS {


	@Autowired
	private ClientStoreService clientStoreService;
	
	@POST
	@Path("/clients/{id}")
	@Consumes("application/xml")
	@Override
	public Response createClient(@RequestBody ClientsAPI client) { 
		System.out.println("..........");
		clientStoreService.createClient(client);

	    return null;
	 }
	
	@PUT
	@Path("/clients/{id}")
	@Consumes("application/xml")
	@Override
	public Response updateClient(@RequestBody int id, ClientsAPI clientsAPI) {
			System.out.println("^^^^^^^^^^^^^^");
			clientStoreService.updateClient(id, clientsAPI);
		return  null;
	}	
	
	@DELETE
	@Path("/clients/{id}")	
	@Consumes("application/xml")
	@Override
	public Response deleteClient(@RequestBody int id) {	
		 System.out.println("..........");
		 clientStoreService.deleteClient(id);		
		return null;
	}
	
	@GET
	@Path("/clients/id/{id}")
	@Produces("application/xml")
	@Override
	public Response findById(@RequestBody int id) {	
		 System.out.println("***********");
		 clientStoreService.findById(id);
		return null;
	}

	@GET
	@Path("/clients/{email}")
	@Produces("application/xml")
	@Override
	public Response findByEmail(@RequestBody String email) {
		System.out.println("##############");
		clientStoreService.findByEmail(email);
		return null;
	}

	@GET
	@Path("/clients")
	@Produces("application/xml")
	@Override
	public List<ClientsAPI> findAll() {
		System.out.println("@@@@@@@@@@@@@@");
		clientStoreService.findAll();
		return null;
	}

	@GET
	@Path("/hi")
	@Produces("application/xml")
	public String sayHi() {
		System.out.println("RRRRRRRRRR");
		return clientStoreService.sayHi();
	}	
}


