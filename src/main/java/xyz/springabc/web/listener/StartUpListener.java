package xyz.springabc.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import xyz.springabc.service.PropertyServ;

/**
 * Application Lifecycle Listener implementation class StartUpListener
 *
 */
public class StartUpListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public StartUpListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  {
    	ServletContext application=event.getServletContext();
    	ApplicationContext applicationContext=WebApplicationContextUtils.getWebApplicationContext(application);
    	PropertyServ propertyServ=applicationContext.getBean(PropertyServ.class);
    	propertyServ.setup(application);//把设置信息都扔到里面，页面可以直接读取
    }
	
}
