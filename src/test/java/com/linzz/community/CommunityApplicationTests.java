package com.linzz.community;

import com.linzz.community.dao.AlphaDao;
import com.linzz.community.dao.AlphaDaoHibernateImpl;
import com.linzz.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes=CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);
		AlphaDao alphaDao=applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());
		alphaDao=(AlphaDao)applicationContext.getBean("alphaDaoHibernateImpl");
		System.out.println(alphaDao);
	}

	@Test
	public void testBeanManagement(){
		AlphaService alphaService = (AlphaService)applicationContext.getBean("alphaService");
		System.out.println(alphaService);
		alphaService = (AlphaService)applicationContext.getBean("alphaService");
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat=(SimpleDateFormat)applicationContext.getBean("simpleDateFormat");
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	AlphaDao alphaDao;
	@Test
	public void testDI(){
		System.out.println(alphaDao);
	}
}
