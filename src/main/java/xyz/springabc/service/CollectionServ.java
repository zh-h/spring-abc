package xyz.springabc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import xyz.springabc.domin.Collection;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;
import xyz.springabc.repository.CollectionRepo;

@Service
public class CollectionServ {
	@Autowired
	private CollectionRepo collectionRepo;
	
	/**
	 * 按照用户获取搜藏
	 * @param user
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Collection> getByUserId(User user,int page,int size){
		PageRequest pageRequest=new PageRequest(--page, size);
		return collectionRepo.findByUserOrderByCreateAtDesc(user, pageRequest);
	}
	
	/**
	 * 判断是否收藏
	 * @param topic
	 * @param user
	 * @return
	 */
	public boolean isCollected(Topic topic,User user){
		return collectionRepo.findOneByTopicAndUser(topic, user)!=null?true:false;
	}
	
	/**
	 * 创建收藏
	 * @param topic
	 * @param user
	 * @return
	 */
	public Collection collect(Topic topic,User user){
		Collection collection=new Collection();
		collection.setCreateAt(new Date());
		collection.setTopic(topic);
		collection.setUser(user);
		return collectionRepo.save(collection);
	}
	
	/**
	 * 取消收藏
	 * @param topic
	 * @param user
	 */
	public void unCollect(Topic topic,User user){
		Collection collection=collectionRepo.findOneByTopicAndUser(topic, user);
		if(collection!=null){
			collectionRepo.delete(collection);
		}
	}
}
