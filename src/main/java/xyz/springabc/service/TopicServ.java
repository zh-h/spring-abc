package xyz.springabc.service;

import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import xyz.springabc.domin.Collection;
import xyz.springabc.domin.Comment;
import xyz.springabc.domin.Focus;
import xyz.springabc.domin.Node;
import xyz.springabc.domin.Section;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;
import xyz.springabc.error.ValidateError;
import xyz.springabc.repository.CommentRepo;
import xyz.springabc.repository.NodeRepo;
import xyz.springabc.repository.SectionRepo;
import xyz.springabc.repository.TopicRepo;
import xyz.springabc.repository.UserRepo;

@Service
public class TopicServ {

	public static final int STATUS_DELETE = 2333;

	@Autowired
	private NodeRepo nodeRepo;

	@Autowired
	private TopicRepo topicRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private SectionRepo sectionRepo;

	/**
	 * 设置话题的最后回复人
	 * 
	 * @param topicPage
	 */
	private void setLastCommentUser(Page<Topic> topicPage) {
		for (Topic topic : topicPage.getContent()) {
			User lastCommentUser = userRepo.getLastCommentUserByTopicId(topic.getId());
			if (lastCommentUser != null) {
				topic.setLastCommentUser(lastCommentUser);
			}
			Comment lastComment = commentRepo.getLastCommentByTopicId(topic.getId());
			if (lastComment != null) {
				topic.setLastCommentAt(lastComment.getCreateAt());
			}
		}
	}

	/**
	 * 查找所有分类话题，默认是状态0
	 * 
	 * @param page
	 * @return
	 */
	public Page<Topic> getAllFilteByStatusOrderByDefault(int page) {
		PageRequest pageRequest = new PageRequest(--page, 30);
		Page<Topic> topicPage = topicRepo.findByStatusOrderByLastCommentAtDesc(0, pageRequest);
		this.setLastCommentUser(topicPage);
		return topicPage;
	}

	/**
	 * 查看所有最热
	 * 
	 * @param page
	 * @return
	 */
	public Page<Topic> getAllOrderByHot(int page) {
		PageRequest pageRequest = new PageRequest(--page, 30);
		Page<Topic> topicPage = topicRepo.findAllByStatusOrderByCommentCountDesc(0, pageRequest);
		this.setLastCommentUser(topicPage);
		return topicPage;
	}

	/**
	 * 按照分类默认获取
	 * 
	 * @param sectionId
	 *            分类名
	 * @param page
	 * @return
	 */
	public Page<Topic> getBySectionDefault(String sectionName, int page) {
		PageRequest pageRequest = new PageRequest(--page, 30);
		Page<Topic> topicPage = topicRepo.findByNodeSectionAndStatusOrderByLastCommentAtDesc(sectionName, 0,
				pageRequest);
		this.setLastCommentUser(topicPage);
		return topicPage;
	}

	/**
	 * 按照分类号来查找
	 * 
	 * @param sectionId
	 * @param page
	 * @return
	 */
	public Page<Topic> getBySectionDefault(int sectionId, int page) {
		Section section = new Section();
		section.setId(new Integer(sectionId));
		int status = 0;
		PageRequest pageRequest = new PageRequest(--page, 30);
		Page<Topic> topicPage = topicRepo.findByNodeSectionAndStatusOrderByLastCommentAtDesc(section, status,
				pageRequest);
		this.setLastCommentUser(topicPage);
		return topicPage;
	}

	/**
	 * 最热评论
	 * 
	 * @param sectionId
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Topic> getBySectionHot(int sectionId, int page, int size) {
		Section section = new Section();
		section.setId(new Integer(sectionId));
		int status = 0;
		PageRequest pageRequest = new PageRequest(--page, size);
		Page<Topic> topicPage = topicRepo.findByNodeSectionAndStatusOrderByCommentCountDesc(section, status,
				pageRequest);
		this.setLastCommentUser(topicPage);
		return topicPage;
	}

	/**
	 * 最少评论
	 * 
	 * @param sectionId
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Topic> getBySectionCool(int sectionId, int page, int size) {
		Section section = new Section();
		section.setId(new Integer(sectionId));
		int status = 0;
		PageRequest pageRequest = new PageRequest(--page, size);
		Page<Topic> topicPage = topicRepo.findByNodeSectionAndStatusOrderByCommentCountAsc(section, status,
				pageRequest);
		this.setLastCommentUser(topicPage);
		return topicPage;
	}

	/**
	 * 按照节点名查找，最新/最近
	 * 
	 * @param nodeName
	 * @param page
	 * @return
	 */
	public Page<Topic> getByNodeOrderByDefault(String nodeName, int page) {
		PageRequest pageRequest = new PageRequest(--page, 30);
		Page<Topic> topicPage = topicRepo.findByNodeAndStatusOrderByLastCommentAtDesc(nodeName, 0, pageRequest);
		this.setLastCommentUser(topicPage);
		return topicPage;
	}

	public Page<Topic> getByNodeOrderByDefault(Node node, int page) {
		PageRequest pageRequest = new PageRequest(--page, 30);
		Page<Topic> topicPage = topicRepo.findByNodeAndStatusOrderByLastCommentAtDesc(node, 0, pageRequest);
		this.setLastCommentUser(topicPage);
		return topicPage;
	}
	
	public Page<Topic> getByFocusedOrderByDefault(int userId,int page){
		PageRequest pageRequest = new PageRequest(--page, 30);
		Page<Topic> topicPage = topicRepo.findByFocusedOrderByDefault(userId, pageRequest);
		this.setLastCommentUser(topicPage);
		return topicPage;
	}

	/**
	 * 直接保存话题
	 * 
	 * @param topic
	 * @return
	 */
	public Topic save(Topic topic) {
		return topicRepo.save(topic);
	}

	/**
	 * 输入已有的节点保存话题
	 * 
	 * @param topic
	 * @param nodeName
	 * @param user
	 * @return
	 */
	@Transactional
	public Topic save(Topic topic, String nodeName, User user) {
		Node node = nodeRepo.findByName(nodeName);
		if (node == null) {
			return null;
		}
		topic.setNode(node);
		topic.setUser(user);
		if (topic.getCreateAt() == null) {
			Date date = new Date();
			topic.setCreateAt(date);
		}
		topic.setStatus(0);
		return topicRepo.save(topic);
	}

	/**
	 * 通过id删除话题
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteById(int id) {
		topicRepo.delete(id);
	}

	/**
	 * 软删除 改变状态
	 * 
	 * @param id
	 */
	@Transactional
	public void softDelete(int id) {
		Topic topic = getOne(id);
		if (topic != null) {
			topic.setStatus(STATUS_DELETE);
			topicRepo.save(topic);
		}
	}

	/**
	 * 获取一个话题
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Topic getOne(int id) {
		Topic topic = topicRepo.findOne(new Integer(id));
		topicRepo.plusViewCountById(id);
		return topic;
	}

	/**
	 * 按照用户获取话题
	 * 
	 * @param user
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Topic> getByUser(User user, int page, int size) {
		PageRequest pageRequest = new PageRequest(--page, size);
		return topicRepo.findByUserOrderByCreateAtDesc(user, pageRequest);
	}

	@Transactional
	public Topic update(Topic topic) {
		Topic oldTopic = topicRepo.getOne(topic.getId());
		if (topic != null) {
			oldTopic.setNode(topic.getNode());
			oldTopic.setTitle(topic.getTitle());
			oldTopic.setContent(topic.getContent());
			topicRepo.save(oldTopic);
		}
		return oldTopic;// 更新后的
	}

	public Page<Topic> getByLike(String nick, String title, int page) {
		PageRequest pageRequest = new PageRequest(--page, 30);
		if (title != "") {
			return topicRepo.findByTitleLikeAndStatus(title, 0, pageRequest);
		} else {
			User user = userRepo.findOneByNick(nick);
			return topicRepo.findByUserOrderByCreateAtDesc(user, pageRequest);
		}
	}
}
