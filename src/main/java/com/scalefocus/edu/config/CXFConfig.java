package com.scalefocus.edu.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.scalefocus.edu.api.rs.ClientStoreAPIRSImpl;
import com.scalefocus.edu.api.ws.ClientStoreAPIWSImpl;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml",
		"classpath:META-INF/cxf/cxf-servlet.xml",
		"classpath:META-INF/cxf/cxf-extension-*.xml" })
public class CXFConfig {

	@Autowired
	private Bus bus;

	@Autowired
	private ClientStoreAPIWSImpl clientStoreWS;
	
	@Autowired
	private ClientStoreAPIRSImpl clientStoreRS;


	@PostConstruct
	public void init() {
		// Initializing WS Service endpoint
		Endpoint endpoint = new EndpointImpl(bus, clientStoreWS);
		endpoint.publish("/ws/clientStore");
		
		// initializes JAX-RS provider and service endpoint
		// https://cwiki.apache.org/confluence/display/CXF20DOC/JAXRS+Services+Configuration //
		// https://stackoverflow.com/questions/48327530/apache-cxf-jaxrsserverfactorybean-server-started-but-not-able-to-access-the-en //
		JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
		factory.setBus(bus);
		// setting Jackson as default JSON provider
		factory.setProviders(Arrays.asList(new JacksonJsonProvider()));
		// adding our rest service bean to Jax-rs server
		factory.setServiceBean(clientStoreRS);
		factory.create();

	}
}

