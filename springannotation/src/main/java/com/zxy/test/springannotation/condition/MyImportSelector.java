package com.zxy.test.springannotation.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//返回自定义逻辑需要导入的组件
public class MyImportSelector implements ImportSelector {

    //返回值，就是到导入到容器中的组件全类名
    //AnnotationMetadata:当前标注@Import注解的类的所有注解信息
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // TODO Auto-generated method stub
        //importingClassMetadata
        //方法不要返回null值
        return new String[]{"com.zxy.test.springannotation.bean.Blue","com.zxy.test.springannotation.bean.Yellow"};
    }

}
