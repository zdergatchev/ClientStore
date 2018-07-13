package com.scalefocus.edu.api.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.scalefocus.edu.api.model.AddressesAPI;
import com.scalefocus.edu.db.model.Addresses;
import com.scalefocus.edu.service.ClientStoreService;

@Controller
@WebService(targetNamespace = "http://localhost:8080/ClientStore/ws/addresses?wsdl")
public class AddressesAPIWSImpl {
	
	@Autowired
	private ClientStoreService clientStoreService;
	
	@WebMethod
	@WebResult(name="addresses")
//	@GET
//	@Path("/addresses/id/{id}")
//	@Produces("application/json")
//	@Override
	public List<AddressesAPI> showAll(@WebParam(name = "id") int id) {
		System.out.println("$$$$$$$$$$$$$");			
		List<AddressesAPI> allAddresses = clientStoreService.showAll(id);
//		return Response.status(200).entity(allAddresses).type(MediaType.APPLICATION_JSON).build();
		return allAddresses;		
	}
	
	@WebMethod
	@WebResult(name="addresses")
	public Addresses createAddresses(@WebParam(name = "id") int id, AddressesAPI addressesAPI) {
		System.out.println("..........");
		Addresses address = clientStoreService.createAddresses(id, addressesAPI);
		System.out.println(address);
//		return Response.status(200).entity(address).type(MediaType.APPLICATION_JSON).build();
		return address;
	}
	
	@WebMethod
	@WebResult(name="addresses")
	public Addresses updateAddresses(@WebParam(name = "id") int id, AddressesAPI addressesAPI) {
		System.out.println("%%%%%%%%%%%%%%");
		Addresses address = clientStoreService.updateAddresses(id, addressesAPI);
//		return Response.status(200).entity(address).type(MediaType.APPLICATION_JSON).build();
		return address;
	}
	
	@WebMethod
	@WebResult(name="addresses")
	public Addresses editAddresses(@WebParam(name = "id") int id, AddressesAPI addressesAPI) {
		System.out.println("~~~~~~~~~~~~~~~");
		Addresses address = clientStoreService.editAddresses(id, addressesAPI);
//		return Response.status(200).entity(address).type(MediaType.APPLICATION_JSON).build();
		return address;
	}
	

	public Addresses deleteAddresses(@WebParam(name = "id") int id, AddressesAPI addressesAPI) {
		 System.out.println("&&&&&&&&&&&&&");	
		 Addresses address = clientStoreService.deleteAddresses(id, addressesAPI);
		 System.out.println(address);
//		 return Response.status(200).entity(address).type(MediaType.APPLICATION_JSON).build();
		 return address;
	}
}
