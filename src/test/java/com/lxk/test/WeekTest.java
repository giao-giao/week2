package com.lxk.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lxk.bean.User;
import com.lxk.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext-redis.xml")
public class WeekTest {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private List<User> userList = new ArrayList<User>();
	
	@Test
	public void testData(){
		
		//循环5万次,生成模拟数据
		for (int i = 0; i < 50000; i++) {
			
			User user = new User();
			
			//获取ID
			user.setId(i+1);
			
			//随机姓名
			String name = StringUtils.getRandomStr(3);
			user.setName(name);
			
			//随机性别
			Random random = new Random();
			String sex = random.nextBoolean()?"男":"女";
			user.setSex(sex);
			
			//随机手机号
			String phone = "13"+StringUtils.getRandomNumber(9);
			user.setPhone(phone);
			
			//随机邮箱
			int random2 = (int) (Math.random()*20);
			String randomStr = StringUtils.getRandomStr(random2);
			String randomEmailSuffex = StringUtils.getRandomEmailSuffex();
			user.setEmail(randomStr+randomEmailSuffex);
			
			//随机生日
			String randomBirthday = StringUtils.randomBirthday();
			user.setBirthday(randomBirthday);
			
			//保存到List集合中
			userList.add(user);
		}
		
		//JDK序列化方法
		System.out.println("JDK序列化方法");
		//获取当前时间 开始和结束时间
		long start = System.currentTimeMillis();
		
		BoundListOperations<String, Object> boundListOps = redisTemplate.boundListOps("jdk");
		//保存到redis中
		long random = 0L;
		for (User user2 : userList) {
			random = boundListOps.leftPush(user2);
		}
		
		long end = System.currentTimeMillis();
		System.out.println(random);
		System.out.println("耗时:"+(end-start)+"毫秒");
		
		/*//JSON序列化方法
		System.out.println("JSON序列化方法");
		//获取当前时间 开始和结束时间
		long start = System.currentTimeMillis();
		
		BoundListOperations<String, Object> boundListOps = redisTemplate.boundListOps("json");
		//保存到redis中
		long random = 0L;
		for (User user2 : userList) {
			random = boundListOps.leftPush(user2);
		}
		
		long end = System.currentTimeMillis();
		System.out.println(random);
		System.out.println("耗时:"+(end-start)+"毫秒");*/
		
		/*//Hash序列化方法
		System.out.println("Hash序列化方法");
		//获取当前时间 开始和结束时间
		long start = System.currentTimeMillis();
		
		BoundHashOperations<String, Object, Object> boundHashOps = redisTemplate.boundHashOps("hash");
		//保存到redis中
		for (User user2 : userList) {
			boundHashOps.put("hash", user2);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("耗时:"+(end-start)+"毫秒");*/
	}
}
