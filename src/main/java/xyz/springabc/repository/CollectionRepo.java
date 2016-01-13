package xyz.springabc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import xyz.springabc.domin.Collection;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;

public interface CollectionRepo extends JpaRepository<Collection, Integer>{

	Page<Collection> findByUserOrderByCreateAtDesc(User user,Pageable pageRequest);
	Collection findOneByTopicAndUser(Topic topic,User user);
}
