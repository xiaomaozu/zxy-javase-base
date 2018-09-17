package com.zxy.test.springannotation.config;

import com.zxy.test.springannotation.bean.Person;
import com.zxy.test.springannotation.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


//配置类等于配置文件
@Configuration   //告诉spring这是一个配置文件
/*指定扫描的规则*/
//@ComponentScan  value:指定要扫描的包
//excludeFilters = Filter[] ：指定扫描的时候按照什么规则排除那些组件
//includeFilters = Filter[] ：指定扫描的时候只需要包含哪些组件
//FilterType.ANNOTATION：按照注解
//FilterType.ASSIGNABLE_TYPE：按照给定的类型；
//FilterType.ASPECTJ：使用ASPECTJ表达式 （表达式）
//FilterType.REGEX：使用正则指定
//FilterType.CUSTOM：使用自定义规则
@ComponentScans(
        value={
                //配置包扫描的
                @ComponentScan(value="com.zxy.test.springannotation",includeFilters = {
                      /*  @Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
                        @Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {BookService.class}),*/
                        @Filter(type = FilterType.CUSTOM,classes = MyTypeFilter.class)
                },useDefaultFilters = false)
        }
)
public class MainConfig {
    //给容器中注册一个bean，类型为返回值类型。id默认是用id作为方法名
    @Bean("person007")
    public Person person01(){
        return new Person("lili",20);
    }
}
