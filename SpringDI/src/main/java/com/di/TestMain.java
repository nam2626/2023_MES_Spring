package com.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class);
		Greeting g = ctx.getBean("greeter",Greeting.class);
		System.out.println(g.getContent());
		System.out.println(g);
		System.out.println(ctx.getBean("greeter",Greeting.class));
	}

}
