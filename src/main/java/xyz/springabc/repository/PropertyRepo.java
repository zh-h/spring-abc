package xyz.springabc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.springabc.domin.Property;

public interface PropertyRepo extends JpaRepository<Property, Integer>{
	
	Property findOneByKeyword(String keyword);
}
