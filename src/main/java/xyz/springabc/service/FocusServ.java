package xyz.springabc.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.springabc.domin.Focus;
import xyz.springabc.domin.Node;
import xyz.springabc.domin.User;
import xyz.springabc.repository.FocusRepo;
import xyz.springabc.repository.NodeRepo;
import xyz.springabc.repository.UserRepo;

@Service
public class FocusServ {
	
	@Autowired
	private FocusRepo focusRepo;
	
	@Autowired
	private NodeRepo noderepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public boolean isFocus(int nodeId,int userId){
		if(focusRepo.findByNodeIdAndUserId(nodeId, userId)==null){
			return false;
		}else{
			return true;
		}
	}
	
	@Transactional
	public boolean focus(int nodeId,int userId){
		Focus focus=new Focus();
		Node node=noderepo.getOne(new Integer(nodeId));
		User user = userRepo.getOne(new Integer(userId));
		if(node!=null&&user!=null){
			focus.setCreateAt(new Date());
			focus.setNode(node);
			focus.setUser(user);
			focusRepo.save(focus);
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	public boolean unFocus(int nodeId,int userId){
		Focus focus=focusRepo.findByNodeIdAndUserId(nodeId, userId);
		if(focus==null){
			return false;
		}else{
			focusRepo.delete(focus);
			return true;
		}
	}
}
