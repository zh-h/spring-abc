package xyz.springabc.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import xyz.springabc.domin.Comment;
import xyz.springabc.domin.Notification;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;
import xyz.springabc.error.ValidateError;
import xyz.springabc.repository.CommentRepo;
import xyz.springabc.repository.NotificationRepo;
import xyz.springabc.repository.TopicRepo;
import xyz.springabc.repository.UserRepo;
import xyz.springabc.web.viewmodel.CommentVM;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServ {
	
	public static final String NOTIFICATION_TYPE_AT="@";
	public static final String NOTIFICATION_TYPE_COMMENT="回复";
	//匹配数字汉字字母下划线，3到9个字符，前后有空格
	public static final Pattern NICK_PATTERN=Pattern.compile(" @([\u4e00-\u9fa5|A-Za-z0-9|_]{3,9}) ");
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private TopicRepo topicRepo;

	@Autowired
	private NotificationRepo notificationRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	private void setLike(List<Comment> comments,int userId){
		for(Comment comment:comments){
			boolean isLike=commentRepo.findByCommentIDAndOneLikeUserId(comment.getId(), userId)==null?false:true;
			comment.setLike(isLike);
		}
	}
	
	/**
	 * 按照话题查找
	 * @param topic
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Comment> getByTopicWithLike(Topic topic,int page, User user){
		PageRequest pageRequest=new PageRequest(--page, 60);
		Page<Comment> commentPage = commentRepo.findByTopic(topic, pageRequest);
		int userId=user==null?0:user.getId();
		setLike(commentPage.getContent(), userId);
		return commentPage;
	}
	
	public Page<Comment> getByTopic(Topic topic,int page){
		PageRequest pageRequest=new PageRequest(--page, 60);
		 return commentRepo.findByTopic(topic, pageRequest); 
	}
	
	/**
	 * 创建评论并同时通知
	 * @param comment
	 * @param contextPath 应用程序的上下文，要request对象提供
	 * @return
	 * @throws ValidateError
	 */
	@Transactional
	public Comment create(Comment comment,int topicId,String contextPath){
		Topic topic=topicRepo.findOne(new Integer(topicId));
		/**
		 * 创建评论对象
		 */
		Long floor=commentRepo.countByTopic(topic);
		Date date=new Date();
		comment.setCreateAt(date);
		comment.setFloor(floor.longValue()+1);//楼层数要比原来加一
		comment.setTopic(topic);
		commentRepo.save(comment);
		
		/**
		 * 匹配替换评论中提起的昵称
		 */
		String newContent=comment.getContent();
		Matcher matcher=NICK_PATTERN.matcher(newContent);
		Set<User> atUsers=new HashSet<User>();
		while(matcher.find()){
			String nick=matcher.group(1);
			User atUser=userRepo.findOneByNick(nick);
			if(atUser!=null){
				atUsers.add(atUser);//确保只能at一次
			}
		}
		
		for(User atUser:atUsers){
			String userLink="<a href=\"{0}/users/{1}/topics\">{2}</a>";
			String replacement=MessageFormat.format(
					userLink, contextPath,atUser.getId(),atUser.getNick());
			newContent=newContent.replaceAll(atUser.getNick(), replacement);//替换at
			Notification atNotification=new Notification();//创建通知
			atNotification.setComment(comment);//设置评论
			atNotification.setType(NOTIFICATION_TYPE_AT);//设置为at类型
			atNotification.setUser(atUser);//设置通知对象
			notificationRepo.save(atNotification);//保存通知
		}
		/**
		 * 通知给作者
		 */
		Notification notification=new Notification();
		notification.setComment(comment);
		notification.setType(NOTIFICATION_TYPE_COMMENT);
		notification.setUser(comment.getUser());
		notificationRepo.save(notification);
		
		/**
		 * 替换at之后更新评论 
		 */
		comment.setContent(newContent);
		commentRepo.save(comment);
		topicRepo.plusCommentCountById(topicId);
		
		return comment;
	}
	
	/**
	 * 根据用户获取评论
	 * @param user
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Comment> getByUser(User user,int page,int size){
		PageRequest pageRequest=new PageRequest(--page, size);
		return commentRepo.findByUserOrderByCreateAtDesc(user, pageRequest);
	}
	
	public Page<Comment> getAll(int page){
		PageRequest pageRequest=new PageRequest(--page, 30);
		return commentRepo.findAll(pageRequest);
	}
	
	public Comment getOne(int id){
		return commentRepo.findOne(new Integer(id));
	}
	
	@Transactional
	public void update(Comment comment){
		Comment oldComment=commentRepo.findOne(comment.getId());
		if(oldComment!=null){
			oldComment.setContent(comment.getContent());
			commentRepo.save(oldComment);
		}
	}
	
	@Transactional
	public void delete(int id){
		commentRepo.delete(new Integer(id));
	}
	
	@Transactional
	public void delete(Comment comment){
		long commentCount=comment.getTopic().getCommentCount();
		Topic topic=comment.getTopic();
		topic.setCommentCount(--commentCount);
		topicRepo.save(topic);
		commentRepo.delete(comment);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean like(Comment comment,User user){
		boolean isLike =commentRepo.findByCommentIDAndOneLikeUserId(comment.getId(), user.getId())==null?false:true;
		if(isLike){
			commentRepo.deleteByLikeUserid(comment.getId(),user.getId());
			comment.setLikeCount(comment.getLikeCount()-1);
			commentRepo.save(comment);
			return false;
		}else{
			comment.getLikeUsers().add(user);
			comment.setLikeCount(comment.getLikeCount()+1);
			commentRepo.save(comment);
			return true;
		}
	}
}
