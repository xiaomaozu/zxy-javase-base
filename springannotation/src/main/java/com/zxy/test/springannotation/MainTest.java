package com.zxy.test.springannotation;

import com.zxy.test.springannotation.bean.Person;
import com.zxy.test.springannotation.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

    //抑制編譯器產生的警告信息
    @SuppressWarnings("resource")
    public static void main(String[] args) {

        //		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //		Person bean = (Person) applicationContext.getBean("person");
        //		System.out.println(bean);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name:beanNamesForType){
            System.out.println(name);
        }
    }
}
