package xyz.springabc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import xyz.springabc.domin.User;

@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("from User u where u.username=?1")
	List<User> findByUsername(String username);

	@Modifying
	@Query("update User u set u.commentCount=u.commentCount+1 where u.id=?1")
	int plusViewCountById(Integer id);

	/*
	 * @Query("select user form User user,Comment comment,Topic topic "+
	 * "where user.id=comment.user "+ "and comment.topic=topic.id "+
	 * "and topic.id= ?1")
	 */
	@Query(value = "select  u.* from topic t,comment c,user u where c.topic_id=t.id and c.user_id=u.id and t.id=?1 order by c.create_at desc  limit 0,1", nativeQuery = true)
	User getLastCommentUserByTopicId(Integer id);

	User findOneByUsername(String username);
	
	User findOneByNick(String nick);
	
	User findOneByEmail(String email);
	
	Page<User> findByRole(String role,Pageable pageRequest);
	
	Page<User> findByNickLike(String nick,Pageable pageRequest);
}
