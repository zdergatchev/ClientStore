package com.scalefocus.edu.db.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scalefocus.edu.db.model.Clients;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ClientsDaoImpl implements ClientsDao{
	
	@PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void createClient(Clients clients) {
        entityManager.persist(clients);
    }

    @Override
    public Clients retrieveClientsById(int id) {
        return entityManager.find(Clients.class,id);
    }

    @Override
    public Clients retrieveClientsByEmail(String email) {
        return entityManager.find(Clients.class,email);
    }
    
    
    @Override
    public List<Clients> retrieveAllClients(){    
        return entityManager.createQuery("SELECT * FROM clients").getResultList();
    }

    @Override
    public void updateClient(int id) {
        entityManager.merge(id);
    }

    @Override
    public void deleteClient(int id) {
        Clients cl = entityManager.find(Clients.class,id);
        entityManager.remove(cl);
    }
}
