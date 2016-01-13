package xyz.springabc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import xyz.springabc.domin.Node;
import xyz.springabc.domin.Section;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;

public interface TopicRepo extends JpaRepository<Topic, Integer>{
	
	long countByNode(Node node);
	/**
	 * 按照状态查找，最新评论/发布
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	@Query("SELECT t FROM Topic t "
			+"WHERE t.status=?1 "
			+"ORDER BY t.lastCommentAt DESC,t.createAt DESC")
	Page<Topic> findAllByStatusOrderByLastCommentAtDesc(int status,Pageable pageRequest);
	
	/**
	 * 按照状态查找，最多评论
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	@Query("SELECT t FROM Topic t "
			+"WHERE t.status=?1 "
			+"ORDER BY t.commentCount DESC")
	Page<Topic> findAllByStatusOrderByCommentCountDesc(int status,Pageable pageRequest);
	
	/**
	 * 按照用户查找，按照最新的创建日期
	 * @param user
	 * @param pagerequest
	 * @return
	 */
	Page<Topic> findByUserOrderByCreateAtDesc(User user,Pageable pagerequest);
	
	/**
	 * 按照节点和状态查找，按照最高分数
	 * @param node
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	Page<Topic> findByNodeAndStatusOrderByScoreDesc(Node node,int status,Pageable pageRequest);
	
	/**
	 * 按照节点和状态查找，按照最新评论时间
	 * 默认话题最后评论为更新时间
	 * @param node
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	Page<Topic> findByNodeAndStatusOrderByLastCommentAtDesc(Node node,int status,Pageable pageRequest);
	
	/**
	 * 按照节点名称和状态查找，按照最新评论时间
	 * @param nodeName
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	@Query("SELECT t FROM Topic t LEFT JOIN t.node "
			+"WHERE t.node.name=?1 AND t.status=?2 "
			+"ORDER BY t.lastCommentAt DESC,t.createAt DESC")
	Page<Topic> findByNodeAndStatusOrderByLastCommentAtDesc(String nodeName,int status,Pageable pageRequest);
	
	/**
	 * 按照节点和状态查找，按照最多浏览数
	 * @param node
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	Page<Topic> findByNodeAndStatusOrderByViewCountDesc(Node node,int status,Pageable pageRequest);
	
	/**
	 * 按照节点和状态查找，按照最多评论数
	 * @param node
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	Page<Topic> findByNodeAndStatusOrderByCommentCountDesc(Node node,int status,Pageable pageRequest);
	
	/**
	 * 按照节点和状态查找，按照最少评论数
	 * @param node
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	Page<Topic> findByNodeAndStatusOrderByCommentCountAsc(Node node,int status,Pageable pageRequest);
	
	/**
	 * 评论数加一
	 */
	@Modifying
	@Query(value="update topic set topic.view_count = topic.view_count + 1 where topic.id = ?1 ",
	nativeQuery=true)
	int plusViewCountById(int id);
	
	/**
	 * 点赞数加一
	 * @param id
	 * @return
	 */
	@Modifying
	@Query(value="update topic set topic.like_count = topic.like_count + 1 where topic.id = ?1",
	nativeQuery=true)
	int plusLikeCountById(int id);
	
	/**
	 * 点赞数加一
	 * @param id
	 * @return
	 */
	@Modifying
	@Query(value="update topic set topic.comment_count = topic.comment_count + 1 where topic.id = ?1",
	nativeQuery=true)
	int plusCommentCountById(int id);
	
	/**
	 * 按照分类查找，最新评论/创建
	 * 更新的话好像不能顶上去
	 */
	@Query(value="SELECT t FROM Topic t LEFT JOIN t.node n LEFT JOIN n.section "
			+"WHERE n.section =?1 AND t.status=?2 "
			+"ORDER BY t.lastCommentAt DESC,t.createAt DESC")
	Page<Topic> findByNodeSectionAndStatusOrderByLastCommentAtDesc(Section section,int status,Pageable pageRequest);
	
	/**
	 * 按照分类名称查找
	 * @param sectionName	分类名称
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	@Query(value="SELECT t FROM Topic t LEFT JOIN t.node n LEFT JOIN n.section "
			+"WHERE n.section.name =?1 AND t.status=?2 "
			+"ORDER BY t.lastCommentAt DESC,t.createAt DESC")
	Page<Topic> findByNodeSectionAndStatusOrderByLastCommentAtDesc(String sectionName,int status,Pageable pageRequest);
	
	/**
	 * 按照分类查找，最多评论
	 * @param section
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	@Query(value="SELECT t FROM Topic t LEFT JOIN t.node n LEFT JOIN n.section "
			+"WHERE n.section =?1 AND t.status=?2 "
			+"ORDER BY t.commentCount DESC")
	Page<Topic> findByNodeSectionAndStatusOrderByCommentCountDesc(Section section,int status,Pageable pageRequest);
	
	/**
	 * 按照分类查找，最少评论
	 * @param section
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	@Query(value="SELECT t FROM Topic t LEFT JOIN t.node n LEFT JOIN n.section "
			+"WHERE n.section =?1 AND t.status=?2 "
			+"ORDER BY t.commentCount ASC")
	Page<Topic> findByNodeSectionAndStatusOrderByCommentCountAsc(Section section,int status,Pageable pageRequest);
	
	/**
	 * 按照状态查找全部，最新/最新评论
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	@Query("SELECT t FROM Topic t WHERE t.status=?1 "
			+"ORDER BY t.lastCommentAt DESC,t.createAt DESC")
	Page<Topic> findByStatusOrderByLastCommentAtDesc(int status,Pageable pageRequest);
	
	/**
	 * 按照相似标题查找
	 * @param title
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	@Query("SELECT t FROM Topic t LEFT JOIN t.user u "
			+"WHERE t.title LIKE ?1 AND t.status=?2 "
			+"ORDER BY t.lastCommentAt DESC,t.createAt DESC")
	Page<Topic> findByTitleLikeAndStatus(String title,int status,Pageable pageRequest);
	
	@Query("SELECT t FROM User u LEFT JOIN u.focuses f "
			+"LEFT JOIN f.node n LEFT JOIN n.topics t "+
			" WHERE u.id=?1 "
			+"ORDER BY t.lastCommentAt DESC,t.createAt DESC")
	Page<Topic> findByFocusedOrderByDefault(int userId,Pageable pageRequest);
}
