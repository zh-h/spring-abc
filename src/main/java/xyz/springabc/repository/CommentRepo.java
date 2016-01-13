package xyz.springabc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import xyz.springabc.domin.Comment;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	@Query(value="select  c.* from comment c left join topic t on c.topic_id=t.id where t.id=?1 order by c.create_at desc limit 0,1",
			nativeQuery=true)
	Comment getLastCommentByTopicId(Integer id);
	
	/**
	 * 查找最近的评论按照用户
	 * @param user
	 * @param pageRequest
	 * @return
	 */
	Page<Comment> findByUserOrderByCreateAtDesc(User user,Pageable pageRequest);
	
	Page<Comment> findByTopic(Topic topic,Pageable pageRequest);
	
	Long countByTopic(Topic topic);
	
	/**
	 * 按照话题类似查找评论
	 * @param title
	 * @param pageRequest
	 * @return
	 */
	@Query("SELECT c FROM Comment c LEFT JOIN c.topic t "
			+"WHERE t.title LIKE ?1 "
			+"ORDER BY c.createAt DESC")
	Page<Comment> findByTopicTitleLikeOrderByCreateAtDesc(String title,Pageable pageRequest);
	
	@Query(value="select c.* from comment c left join comment_user cu on c.id=cu.comment_id "
			+ "left join user u on cu.user_id=u.id "
			+ "where c.id=?1 and u.id=?2"
			,nativeQuery=true)
	Comment findByCommentIDAndOneLikeUserId(int commentId,int UserId);
	
	@Modifying
	@Query(value="delete from `comment_user` where `comment_id`=?1 and `user_id`=?2",
	nativeQuery=true)
	void deleteByLikeUserid(int commentId,int userId);
}
