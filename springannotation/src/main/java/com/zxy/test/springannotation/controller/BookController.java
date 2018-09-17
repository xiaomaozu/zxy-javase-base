package com.zxy.test.springannotation.controller;

import com.zxy.test.springannotation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    public  BookService bookService;
}
