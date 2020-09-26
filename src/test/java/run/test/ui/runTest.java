package run.test.ui;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.CucumberOptions;


@CucumberOptions(features={"src//test//java//features"},
				 glue={"stepDefinations","base"},
				 tags ={"@post"}
)

public class runTest extends AbstractTestNGCucumberTests{}



