package com.java.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features=("src/test/resources/Features"),glue=("com.java.stepDefinition"))

public class TestRunner {

}
