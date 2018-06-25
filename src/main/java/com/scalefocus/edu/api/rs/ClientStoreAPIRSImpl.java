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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.scalefocus.edu.api.ClientStoreAPI;
import com.scalefocus.edu.api.model.ClientsAPI;
import com.scalefocus.edu.service.ClientStoreService;

/* https://stackoverflow.com/questions/13594945/how-correctly-produce-json-by-restful-web-service */
/* http://users.cxf.apache.narkive.com/9PfDGlYi/cxf-jax-rs-client-sends-post-put-fine-get-delete-hang */
/* Java EE 8 Recipes A Problem-Solution Approach_2ed_p633 */

@Produces("application/json")
@Consumes({"application/json"})
@Path("/rs")
@Controller

public class ClientStoreAPIRSImpl implements ClientStoreAPI {
	public static final String REST_BASE_URI = "http://localhost:8080/ClientStore/";
	   
    static RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private ClientStoreService clientStoreService;

/*	@GET
	@Path("/hi")
	public String sayHi() {
		return clientStoreService.sayHi();
	}	*/
	
	@POST
	@Path("/clients")
//	@Consumes(MediaType.APPLICATION_JSON)
	@Consumes("application/json")	// specifies the request body content
	@Produces("application/json")
	@Override
//	public ClientsAPI createClient(ClientsAPI client) {
	public void createClient() {
		ClientsAPI client = new ClientsAPI();
		client.setId(1);
		client.setEmail("pesho_petrov@mail.bg");
		client.setFirstName("Pesho");
		client.setLastName("Petrov");
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<ClientsAPI> entity = new HttpEntity<>(client,headers);
        restTemplate.postForObject(REST_BASE_URI+"/create", entity,ClientsAPI.class);
		
		//return clientStoreService.createClient(client);
	}
	
	@PUT
	@Path("/clients/{id}")
	@Consumes("application/json")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces("application/json")
	@Override
//	public ClientsAPI updateClient(@PathParam("id") long id, ClientsAPI client) {
//		return clientStoreService.updateClient(id, client);
	public void updateClient(@PathParam("id") int id) {
		ClientsAPI client = new ClientsAPI();
		client.setId(5);
		client.setEmail("ivan_ivanov@abv.bg");
		client.setFirstName("Ivan");
		client.setLastName("Ivanov");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<ClientsAPI> entity = new HttpEntity<>(client,headers);
        
        restTemplate.put(REST_BASE_URI + "/update", entity,ClientsAPI.class);
	}
	
	@DELETE
	@Path("/clients/{id}")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces("application/json")
	@Override
	public void deleteClient(@PathParam("id") int id) {
		restTemplate.delete(REST_BASE_URI + "/delete/"+id);
//		clientStoreService.deleteClientById(id); 
	}

	@GET
//	@Path("/clients/{param}")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces("application/json")
	@Override
	public List<ClientsAPI> retrieveAllClients(){
		
		 List<Map<String, Object>> studentList = restTemplate.getForObject(REST_BASE_URI + "/students", List.class);
	        if (studentList != null)
	        {
	            System.out.println("**** All Students ****");
	            for (Map<String, Object> map : studentList)
	            {
	                System.out.println("Id : id=" + map.get("id") + "   Name=" + map.get("name") + "   Age="
	                        + map.get("age"));
	            }
	        } else
	        {
	            System.out.println("No Students exist!!");
	        }
		//return clientStoreService.retrieveClientsData();
	}

	@GET
	@Path("/clients/{id}")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces("application/json")
	@Override
	public ClientsAPI retrieveClientsById(@PathParam("id") int id) {
		return	restTemplate.getForObject(REST_BASE_URI+"/client/"+ id, ClientsAPI.class);
//		return clientStoreService.findClients();
	}
	
	@GET
	@Path("/clients/{email}")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces("application/json")
	@Override
	public ClientsAPI retrieveClientsByEmail(@PathParam("email") String email) {
		return restTemplate.getForObject(REST_BASE_URI+"/client/"+ email, ClientsAPI.class);
//		return clientStoreService.findClients();
	}
}
