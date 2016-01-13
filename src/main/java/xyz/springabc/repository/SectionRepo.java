package xyz.springabc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.springabc.domin.Section;

public interface SectionRepo extends JpaRepository<Section, Integer>{
	Section findOneByName(String name);
}
