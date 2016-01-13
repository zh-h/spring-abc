package xyz.springabc.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.springabc.domin.Field;
import xyz.springabc.domin.Property;
import xyz.springabc.repository.FieldRepo;
import xyz.springabc.repository.PropertyRepo;

@Service
public class PropertyServ {

	@Autowired
	private PropertyRepo propertyRepo;

	@Autowired
	private FieldRepo fieldRepo;
	
	private ServletContext application;

	/**
	 * 启动监听设置这个属性
	 * @param application
	 */
	public void setup(ServletContext application){
		this.application=application;
		for(Property property:propertyRepo.findAll()){
			application.setAttribute(property.getKeyword(), fieldRepo.findOneByProperty(property));
		}
	}
	
	public void afterUpdate(String key,Object object) {
		application.setAttribute(key,object);
	}

	public void update(Map<String, String[]> parames) {
		Set<String> keys = parames.keySet();
		for (String key : keys) {
			String[] values = parames.get(key); //参数值
			Property property = propertyRepo.findOneByKeyword(key);
			if (property != null) {
				Field field = fieldRepo.findOneByProperty(property);//先获取第一个参数值
				field.setContent(values[0]);//以后要拓展列表参数
				fieldRepo.save(field);
				afterUpdate(property.getKeyword(), field);
			}
		}
	}

	public void test() {
		System.out.println("233333");
	}
}
