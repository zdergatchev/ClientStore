package com.scalefocus.edu.api.ws;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.scalefocus.edu.api.ClientStoreAPI;
import com.scalefocus.edu.api.model.ClientsAPI;
import com.scalefocus.edu.service.ClientStoreService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Controller
@WebService
@Path("/ws")
@XmlRootElement(name = "clients")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientStoreAPIWSImpl implements ClientStoreAPI {
	
	@Autowired
	private ClientStoreService clientStoreService;

/*	public String sayHi() {
		return clientStoreService.sayHi();
	}	*/
	
	
	
}


