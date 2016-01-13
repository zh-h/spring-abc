package xyz.springabc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import xyz.springabc.domin.Notification;
import xyz.springabc.domin.User;

public interface NotificationRepo extends JpaRepository<Notification, Integer>{
	Page<Notification>	findByUserAndStatus(User user,int status,Pageable pageRequest);
	long countByUserAndStatus(User user,int status);
}
