package com.java.stepDefinition;

import cucumber.api.java.en.Given;

public class EteamDefinition {

	@Given("^I want to write a step with precondition$")
	public void write() {
		System.out.println("cucumber");
	}
}
