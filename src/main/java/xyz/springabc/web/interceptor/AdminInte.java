package xyz.springabc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import xyz.springabc.domin.User;
import xyz.springabc.service.UserServ;

public class AdminInte implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			return false;
		}else{
			if(!user.getRole().equals(UserServ.ROLE_ADMIIN)){
				return false;
			}else{
				return true;
			}
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
