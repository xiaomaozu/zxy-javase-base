package com.zxy.test.springannotation.service;

import com.zxy.test.springannotation.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {


    //@Qualifier("bookDao")         //可以明确指定要装配的组件名
    //@Autowired(required=false)    //required=false是否必须。默认是true
    //@Resource(name="bookDao2")     //java规范的注解
//    @Inject                        //java规范的注解
    private BookDao bookDao;

    public void print(){
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "BookService [bookDao=" + bookDao + "]";
    }





}

