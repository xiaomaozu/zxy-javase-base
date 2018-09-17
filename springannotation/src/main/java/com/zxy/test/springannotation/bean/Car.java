package com.zxy.test.springannotation.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Car {

	public Car(){
		System.out.println("car constructor...");
	}
	
	public void init(){
		System.out.println("car ... init...");
	}
	
	public void detory(){
		System.out.println("car ... detory...");
	}

}
