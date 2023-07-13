package com.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	@Bean
	public Greeting greeter() {
		Greeting g = new Greeting(1, "test");
		return g;
	}
}
