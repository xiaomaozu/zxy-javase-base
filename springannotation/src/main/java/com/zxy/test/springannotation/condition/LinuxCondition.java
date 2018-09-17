package com.zxy.test.springannotation.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

//判断是否linux系统
public class LinuxCondition implements Condition{

    /**
     * ConditionContext：判断条件能使用的上下文（环境）
     * AnnotatedTypeMetadata：注释信息
     */
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // TODO  是否是linux系统
        // 1.能否去到ioc使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        
        //2.获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //3.获取当前环境信息
        Environment environment = conditionContext.getEnvironment();
        //4.获取bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        String property = environment.getProperty("os.name");
        //可以判断容器中的bean注册情况，也可以给容器中注册bean
        boolean definition = registry.containsBeanDefinition("person");
        if(property.contains("linux")){
            return true;
        }
        return false;
    }
}
