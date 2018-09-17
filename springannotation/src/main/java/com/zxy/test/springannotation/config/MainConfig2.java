package com.zxy.test.springannotation.config;

import com.zxy.test.springannotation.bean.Color;
import com.zxy.test.springannotation.bean.ColorFactoryBean;
import com.zxy.test.springannotation.bean.Person;
import com.zxy.test.springannotation.bean.Red;
import com.zxy.test.springannotation.condition.LinuxCondition;
import com.zxy.test.springannotation.condition.MyImportBeanDefinitionRegistrar;
import com.zxy.test.springannotation.condition.MyImportSelector;
import com.zxy.test.springannotation.condition.WindowsCondition;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;


//配置类等于配置文件
@Conditional({LinuxCondition.class})   //类中组件统一设置。满足当前条件，这个类中配置的所有bean注册才能生效；
@Configuration   //告诉spring这是一个配置文件
@Import({Color.class,Red.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})  //导入组件，id默认是组件的全类名
public class MainConfig2 {

    //默认是单实例的
    /**
     * ConfigurableBeanFactory#SCOPE_PROTOTYPE proptotype
     * @seeConfigurableBeanFactory#SCOPE_SINGLETON singleton
     * @seeorg.springframework.web.context.WebApplicationContext#SCOPE_REQUEST  request
     * @seeorg.springframework.web.context.WebApplicationContext#SCOPE_SESSION	 sesssion
     * @return\
     * @Scope:调整作用域
     * prototype：多实例的：ioc容器启动并不会去调用方法创建对象放在容器中。
     * 					每次获取的时候才会调用方法创建对象；
     * singleton（默认值）：单实例的（默认值）：ioc容器启动会调用方法创建对象放到ioc容器中。
     * 			以后每次获取就是直接从容器（map.get()）中拿，
     * request：同一次请求创建一个实例(比较少用到)
     * session：同一个session创建一个实例(比较少用到)
     *
     * 懒加载：
     *       @Lazy
     * 		只针对单实例bean：默认在容器启动的时候创建对象；
     * 		懒加载：容器启动不创建对象。第一次使用(获取)Bean创建对象，并初始化；
     *
     */
    //给容器中注册一个bean，类型为返回值类型。id默认是用id作为方法名
    /*
        @Scope("prototype")
        @Lazy
    */
    @Bean("person")
    public Person person(){
        System.out.println("给容器添加bean  ----");
        return new Person("张三",20);
    }
        /**
         * @Conditional({Condition}) ： 按照一定的条件进行判断，满足条件给容器中注册bean
         *
         * 如果系统是windows，给容器中注册("bill")
         * 如果是linux系统，给容器中注册("linus")
         */
//        @Conditional({WindowsCondition.class})  2.可以移动到类的‘最上方’
        @Bean("bill")
        public Person person01(){
            return new Person("bill getes", 62);
        }

        @Conditional({LinuxCondition.class})
        @Bean("linus")
        public Person person02(){
            return new Person("linus", 48);
        }

    /**
     * 给容器中注册组件；
     * 1）、包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
     * 2）、@Bean[导入的第三方包里面的组件]
     * 3）、@Import[快速给容器中导入一个组件]
     * 		1）、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
     * 	(使用比较多)	2）、ImportSelector:返回需要导入的组件的全类名数组；
     * 		3）、ImportBeanDefinitionRegistrar:手动注册bean到容器中
     * 4）、使用Spring提供的 FactoryBean（工厂Bean）;
     * 		1）、默认获取到的是工厂bean调用getObject创建的对象
     * 		2）、要获取工厂Bean本身，我们需要给id前面加一个&
     * 			&colorFactoryBean
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }

}
