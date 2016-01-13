package xyz.springabc.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import xyz.springabc.domin.Follow;
import xyz.springabc.domin.User;
import xyz.springabc.repository.FollowReop;

@Service
public class FollowServ {
	
	@Autowired
	private FollowReop followReop;
	
	/**
	 * 获取追随者
	 * 通过userByFollower 追随着
	 * @param user 当前用户
	 * @return
	 */
	public Page<Follow> getFollowerByUser(User user,int page){
		PageRequest pageRequest=new PageRequest(--page, 30);
		return followReop.findByUserByFollowingIdOrderByCreateAtDesc(user, pageRequest);
	}
	
	/**
	 * 查找用户的追随着的用户，
	 * 通过userByFollowingId获取用户的追随者
	 * following指的是当前用户关的追随着
	 * follower指的是关注了当前用户追随者
	 * @param user 当前用户
	 * @return
	 */
	public Page<Follow> getFollowingByUser(User user,int page){
		PageRequest pageRequest=new PageRequest(--page, 30);
		return followReop.findByUserByFollowerIdOrderByCreateAtDesc(user,pageRequest);
	}
	
	/**
	 * 追随
	 * @param self
	 * @param other
	 * @return
	 */
	@Transactional
	public Follow follow(User self,User other){
		Follow follow=new Follow();
		follow.setCreateAt(new Date());
		follow.setUserByFollowerId(self);
		follow.setUserByFollowingId(other);
		return followReop.save(follow);
	}
	
	/**
	 * 不再追随
	 * @param self
	 * @param other
	 */
	@Transactional
	public void unfollow(User self,User other){
		Follow follow=followReop.findOneByUserByFollowerIdAndUserByFollowingId(self, other);
		if(follow!=null){
			followReop.delete(follow);
		}
	}
	
	/**
	 * 判断是不是追随着
	 * @param self
	 * @param other
	 * @return
	 */
	public boolean isFollowing(User self,User other){
		Follow follow=followReop.findOneByUserByFollowerIdAndUserByFollowingId(self, other);
		return follow!=null?true:false;
	}
}
