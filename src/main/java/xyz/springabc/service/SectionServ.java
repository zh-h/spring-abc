package xyz.springabc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.springabc.domin.Section;
import xyz.springabc.repository.SectionRepo;

@Service
public class SectionServ {
	
	@Autowired
	private SectionRepo sectionRepo;
	
	/**
	 * 获取所有分类
	 * @return
	 */
	public List<Section> getAll(){
		return sectionRepo.findAll();
	}
	
	public Section getOne(String name){
		return sectionRepo.findOneByName(name);
	}
	
	public Section save(Section section){
		sectionRepo.save(section);
		return section;
	}
}
