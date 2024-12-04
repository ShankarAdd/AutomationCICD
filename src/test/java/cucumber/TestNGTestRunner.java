package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue = "shankarnrraibagi.stepDefinition",monochrome = true,tags = "@ErrorValidation",plugin = {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
//just added comment to see CICD
}
