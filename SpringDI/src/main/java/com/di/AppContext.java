package com.di;

public class AppContext {

	public Greeting greeter() {
		Greeting g = new Greeting(1, "test");
		return g;
	}
}
