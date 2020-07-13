package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
		(
				features = ".//Features/",//for single feature
				//features = ".//Features/",//for all features
				//features = {".//Features//Login.feature", ".//Features//Customers.feature"},//for multiple feature
				glue = "stepDefinitions",
				dryRun = false,
				monochrome = true,
				plugin = {"pretty", "html:test-output"},
				tags="@sanity or @regression"
		)
public class Runner {
	
}
