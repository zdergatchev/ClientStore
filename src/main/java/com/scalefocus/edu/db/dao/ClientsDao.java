package com.scalefocus.edu.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalefocus.edu.db.model.Clients;

/* http://www.javainterviewpoint.com/spring-restful-web-services-crud-example/ */
/* https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html */
/* https://stackoverflow.com/questions/14014086/what-is-difference-between-crudrepository-and-jparepository-interfaces-in-spring */

@Repository
public interface ClientsDao extends JpaRepository<Clients, String>{
	public Clients retrieveClientsById(int id);  			// GET
	public Clients retrieveClientsByEmail(String email);  	// GET
	public List<Clients> retrieveAllClients();				// GET
	public void createClient(Clients clients);				// POST
	public void updateClient(int id);						// PUT							  					
	public void deleteClient(int id);						// DELETE
}
