package com.nowcoder.community;

import com.nowcoder.community.config.alphaconfig;
import com.nowcoder.community.dao.alphdao;
import com.nowcoder.community.service.alphaservice;
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
//配置类
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

//	@Test
//	void contextLoads() {
//	}
//	-------------------------------------------------------------------------------------------------------
	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
//	-------------------------------------------------------------------------------------------------------
	@Test
	public void testApplicationContext()
	{
		System.out.println(applicationContext);
		alphdao alphDao=applicationContext.getBean(alphdao.class);
		System.out.println(alphDao.select());
	}
	@Test
	public void testBeanManagement()
	{
		alphaservice alphaservice=applicationContext.getBean(com.nowcoder.community.service.alphaservice.class);
		System.out.println(alphaservice);
	}
	@Test
	public void testBeanalphaconfig()
	{
		SimpleDateFormat simpleDateFormat=applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}
//	-------------------------------------------------------------------------------------------------------
//把alphdao 注入 alphDao 后面直接使用属性
	@Autowired
	private alphdao alphDao;
	@Autowired
	private alphaservice alphaService;
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	@Test
	public void testDI()
	{
		System.out.println(alphDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}
}
