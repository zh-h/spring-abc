package xyz.springabc.service;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import xyz.springabc.repository.ThePageRepo;

@Service
public class ThePageServ {
	
	@Autowired
	private ThePageRepo thePageRepo;
	
	public Page<xyz.springabc.domin.ThePage> getPage(int page){
		Pageable pageRequest=new PageRequest(--page, 30);
		return thePageRepo.findAll(pageRequest);
	}
	
	public void save(xyz.springabc.domin.ThePage thePage){
		thePage.setCreateAt(new Date());
		thePageRepo.save(thePage);
	}
	
	@Cacheable(cacheNames="servCache", key="#url")
	public xyz.springabc.domin.ThePage getOneByUrl(String url){
		return thePageRepo.findOneByUrl(url);
	}
	
	public xyz.springabc.domin.ThePage getOne(int id){
		return thePageRepo.findOne(new Integer(id));
	}
	
	public void delete(int id){
		xyz.springabc.domin.ThePage thePage=thePageRepo.getOne(new Integer(id));
		if(thePage!=null){
			thePageRepo.delete(thePage);
		}
	}
	
	@CacheEvict(cacheNames="servCache",key="#thePage.url")
	public void update(xyz.springabc.domin.ThePage thePage){
		xyz.springabc.domin.ThePage oldPage=thePageRepo.findOne(thePage.getId());
		oldPage.setTitle(thePage.getTitle());
		oldPage.setContent(thePage.getContent());
		oldPage.setUrl(thePage.getUrl());
		oldPage.setUpdateAt(new Date());
		thePageRepo.save(thePage);
	}
	
	/**
	 * 浏览数加一
	 * @param page
	 */
	public void view(xyz.springabc.domin.ThePage thePage){
		thePageRepo.plusViewCount(thePage.getId());
	}
}
