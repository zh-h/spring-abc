package xyz.springabc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.springabc.domin.Field;
import xyz.springabc.domin.Property;

public interface FieldRepo extends JpaRepository<Field, Integer>{
	
	Field findOneByProperty(Property property);
}
