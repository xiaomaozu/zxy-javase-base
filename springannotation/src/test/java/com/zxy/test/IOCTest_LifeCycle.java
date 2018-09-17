package com.zxy.test;

import com.zxy.test.springannotation.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class IOCTest_LifeCycle {
	
	@Test
	public void test01(){
		//1.从一个或多个基于java配置类中的加载Spring应用上下文对象
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
		System.out.println("容器初始化");
		
		applicationContext.getBean("car");
		applicationContext.close();
	}

}
