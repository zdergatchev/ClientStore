package com.scalefocus.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalefocus.edu.api.model.ClientsAPI;
import com.scalefocus.edu.db.dao.ClientsDao;
import com.scalefocus.edu.db.model.Clients;

@Service
public class ClientStoreService{

	@Autowired
	private DozerBeanMapper mapper;
	
	
}
