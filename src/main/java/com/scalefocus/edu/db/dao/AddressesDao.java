package com.scalefocus.edu.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.scalefocus.edu.api.model.AddressesAPI;
import com.scalefocus.edu.db.model.Addresses;
import com.scalefocus.edu.db.model.Clients;

/* https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html */
/* https://stackoverflow.com/questions/14014086/what-is-difference-between-crudrepository-and-jparepository-interfaces-in-spring */

//@Repository
@Service
public interface AddressesDao extends CrudRepository<Addresses, Integer>{	

	Addresses findById(int id); // GET
}

