package xyz.springabc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.springabc.repository.FieldRepo;
import xyz.springabc.repository.PropertyRepo;

@Service
public class FieldServ {
	
	@Autowired
	private FieldRepo fieldRepo;
	
	@Autowired
	private PropertyRepo propertyRepo;
}
