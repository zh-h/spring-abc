package xyz.springabc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import xyz.springabc.domin.Notification;
import xyz.springabc.domin.User;
import xyz.springabc.repository.NotificationRepo;

@Service
public class NotificationServ {
	
	public static final int NONE=0;
	
	public static final int ALL=1;
	
	@Autowired
	private NotificationRepo notificationRepo;
	
	/**
	 * 按照读取状态获取通知
	 * @param user
	 * @param status NONE ALL
	 * @return Page封装的通知
	 */
	public Page<Notification> getByUser(User user,int status,int page){
		int size=30;
		PageRequest pageRequest=new PageRequest(--page, size);
		return notificationRepo.findByUserAndStatus(user,status, pageRequest);
	}
	
	/**
	 * 标记已读文章
	 * @param notifications
	 */
	@Transactional
	public void readAll(List<Notification> notifications){
		for(Notification notification:notifications){
			notification.setStatus(1);//1 为已读
			notificationRepo.save(notification);
		}
	}
	
	/**
	 * 统计未读通知
	 * @param user
	 * @return
	 */
	public long countNotificationNoneRead(User user){
		return notificationRepo.countByUserAndStatus(user, NotificationServ.NONE);
	}
}
