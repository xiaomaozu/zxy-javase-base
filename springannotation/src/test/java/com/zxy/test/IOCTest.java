package com.zxy.test;

import com.zxy.test.springannotation.bean.Blue;
import com.zxy.test.springannotation.bean.Person;
import com.zxy.test.springannotation.config.MainConfig;
import com.zxy.test.springannotation.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class IOCTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void testImport(){
        printBeans(applicationContext);
        Blue bean = applicationContext.getBean(Blue.class);
        System.out.println(bean);

        //工厂Bean获取的是调用getObject创建的对象
       Object bean2 = applicationContext.getBean("colorFactoryBean");
        Object bean3 = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean的类型："+bean2.getClass());
        System.out.println(bean2 == bean3);
/*
        Object bean4 = applicationContext.getBean("&colorFactoryBean");
        System.out.println(bean4.getClass());*/
    }

    private void printBeans(AnnotationConfigApplicationContext applicationContext){
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    @SuppressWarnings("resource")
    @Test
    public void test03() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
    }

    @SuppressWarnings("resource")
    @Test
    public void test02(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        System.out.println("IOC容器创建完成");
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }

        //在ioc容器中默认是单实例的
      Object person = applicationContext.getBean("person");
        Object person2 = applicationContext.getBean("person");
        System.out.println(person==person2);
    }

    @SuppressWarnings("resource")
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}
