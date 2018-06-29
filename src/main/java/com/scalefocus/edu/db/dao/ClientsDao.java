package com.scalefocus.edu.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.scalefocus.edu.db.model.Clients;

/* http://www.javainterviewpoint.com/spring-restful-web-services-crud-example/ */
/* https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html */
/* https://stackoverflow.com/questions/14014086/what-is-difference-between-crudrepository-and-jparepository-interfaces-in-spring */

@Service
public interface ClientsDao extends CrudRepository<Clients, Integer> {
	
	Clients findById(int id); // GET

	Clients findByEmail(String email); // GET

	List<Clients> findAll(); // GET	
	
	
	//Clients createClient(Clients clients); // POST

	//Clients updateClient(int id); // PUT

	//Clients delete(int id); // DELETE

}
