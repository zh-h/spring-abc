package xyz.springabc.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import xyz.springabc.domin.User;
import xyz.springabc.error.ValidateError;
import xyz.springabc.repository.UserRepo;
import xyz.springabc.service.support.EncryptUtil;
import xyz.springabc.web.form.UserResetForm;
import xyz.springabc.web.helper.MyMailSender;
import xyz.springabc.web.helper.Validator;

@Service
public class UserServ {

	public static final String ROLE_ADMIIN="admin";
	
	public static final String ROLE_MENBER="menber";
	
	public static final String AVATAR="";
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private MyMailSender myMailSender;
	
	public User getUserByUserName(String username){
		//TODO
		return userRepo.getLastCommentUserByTopicId(new Integer(1));
	}
	
	public Page<User> getFollowers(User user,Pageable page){
		PageRequest pageRequest=new PageRequest(0, 1);
		return userRepo.findAll(pageRequest);
	}
	
	@Transactional
	public	User signup(User user,String password1,Errors errors){
		if(errors.hasErrors()){
			return null;
		}
		String username=user.getUsername();
		String password=user.getPassword();
		String email=user.getEmail();
		if(userRepo.findOneByUsername(username)!=null){
			errors.rejectValue("username", "username", "用户名已经被注册");
		}else if(userRepo.findOneByEmail(email)!=null){
			errors.rejectValue("email", "email", "邮箱已经被注册");
		}else if(!password.equals(password1)){
			errors.rejectValue("password", "password","密码不匹配");
		}
		if(errors.hasErrors()){
			return null;
		}
		user.setNick(username);//默认的昵称就是用户名
		user.setPassword(EncryptUtil.encryptUsernameAndPassword(username, password));
		user.setAvatar("http://tp2.sinaimg.cn/3888714809/180/5727878850/1");
		user.setNumber(userRepo.count());
		user.setCreateAt(new Date());
		return userRepo.save(user);
	}
	
	public User signin(User user,Errors errors){
		String username=user.getUsername();
		String password=EncryptUtil.encryptUsernameAndPassword(username, user.getPassword());
		User userInDatabase;
		if(Validator.isEmail(username)){
			if((userInDatabase=userRepo.findOneByEmail(username))==null){
				errors.rejectValue("username", "username", "邮箱不存在");
				return null;
			}
		}else{
			if((userInDatabase=userRepo.findOneByUsername(username))==null){
				errors.rejectValue("username", "username", "用户名不存在");
				return null;
			}
		}
		if(!userInDatabase.getPassword().equals(password)){
			errors.rejectValue("password", "password", "密码不匹配");
			return null;
		}
		return userInDatabase;
	
	}
	
	public void changeRole(User user,String role){
		user.setRole(role);
		userRepo.save(user);
	}
	/**
	 * 更新用户信息
	 * @param user 包含部分更新属性的暂时用户
	 * @return 更新后用户
	 * @throws ValidateError 
	 */
	@Transactional
	public User update(User oldUser,User newUser,Errors errors){
		if(errors.hasErrors()){
			return null;
		}
		//旧信息和信息的昵称不相同，并且数据库已经有了这样的昵称
		if(userRepo.findOneByNick(newUser.getNick())!=null&&!newUser.getNick().equals(oldUser.getNick())){
			errors.rejectValue("nick", "nick", "昵称已经存在");
			return null;
		}
			//引用复制更新属性
		oldUser.setNick(newUser.getNick());
		oldUser.setAvatar(newUser.getAvatar());
		oldUser.setLocation(newUser.getLocation());
		oldUser.setHomePage(newUser.getHomePage());
		oldUser.setGithub(newUser.getGithub());
		oldUser.setTwitter(newUser.getTwitter());
		oldUser.setSignature(newUser.getSignature());
		oldUser.setDescription(newUser.getDescription());
		userRepo.save(oldUser);
		//更新了旧用户
		return oldUser;
	}
	
	/**
	 * 按照用户编号查找
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames="servCache",key="#id")
	public User getByUserId(int id){
		return userRepo.findOne(new Integer(id));
	}
	
	/**
	 * 校验更新用户密码
	 * @param id	用户id
	 * @param password0	原密码	
	 * @param password1 新密码
	 * @param password2 确认新密码
	 * @return 更新的用户
	 * @throws Errors 校验信息
	 */
	@Transactional
	public User updatePassword(UserResetForm userRestForm,User user,
			Errors errors){
		String password;
		if(userRestForm.getPassword()==null){
			password=EncryptUtil.encryptUsernameAndPassword(userRestForm.getUsername(), userRestForm.getPassword0());
		}else{
			password=userRestForm.getPassword();
		}
		String password1 =userRestForm.getPassword1();
		String password2 = userRestForm.getPassword2();
		if(!password.equals(user.getPassword())){
			errors.rejectValue("password0", "password0", "密码不正确");
			return null;
		}else if(!password1.equals(password2)){
			errors.rejectValue("password1", "password1", "密码不匹配");
			return null;
		}
		//如果没有错误
		user.setPassword(EncryptUtil.encryptUsernameAndPassword(user.getUsername(),password1));
		userRepo.save(user);
		return user;
	}
	
	/**
	 * 查找用户
	 * @param msg
	 * @return
	 */
	public User getUserByNickOrEmailOrUsername(String msg){
		User user1=userRepo.findOneByNick(msg);
		User user2=userRepo.findOneByEmail(msg);
		User user3=userRepo.findOneByUsername(msg);
		if(user1!=null){
			return user1;
		}else if(user2!=null){
			return user2;
		}else if(user3!=null){
			return user3;
		}else {
			return null;
		}
	}
	
	/**
	 * 发送校验邮件
	 * @param email
	 * @param code
	 * @return
	 */
	public boolean sendValidateEmail(String email,String code){
		String content="";
		try {
			Resource resource=new ClassPathResource("mailTemplet/code.html");//读取校验码模板
			File file=resource.getFile();
			InputStream urlStream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(urlStream, "UTF-8"));
			//编码只能这样写,不能是utf8;<meta charset="utf8">必须要
			String temp = ""; //每一行的缓存
			while ((temp = bufferedReader.readLine()) != null) {
				content += temp;
			}
			bufferedReader.close();
			content=String.format(content, code);
		} catch (Exception e) {
			return false;
		}
		return myMailSender.send(email, "找回密码，请查看身份校验验证码. Spring abc", content);//邮件接收者，标题，内容
	}
	
	public Page<User> getByRole(String role ,int page){
		Pageable pagerequest=new PageRequest(--page, 30);
		return userRepo.findByRole(role, pagerequest);
	}
	
	public Page<User> getAll(int page){
		Pageable pagerequest=new PageRequest(--page, 30);
		return userRepo.findAll(pagerequest);
	}
	
	public Page<User> getByNickLike(String nick,int page){
		Pageable pagerequest=new PageRequest(--page, 30);
		return userRepo.findByNickLike(nick, pagerequest);
	}
}
