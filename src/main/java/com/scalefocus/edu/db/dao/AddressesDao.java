package com.scalefocus.edu.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalefocus.edu.db.model.Addresses;

/* https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html */
/* https://stackoverflow.com/questions/14014086/what-is-difference-between-crudrepository-and-jparepository-interfaces-in-spring */

@Repository
public interface AddressesDao extends JpaRepository<Addresses, String>{		
}

