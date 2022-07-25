package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/Resources/Features",
    glue = "stepdefs",
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)
public class testRunner extends AbstractTestNGCucumberTests{

}
