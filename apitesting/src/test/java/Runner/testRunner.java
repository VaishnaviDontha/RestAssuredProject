package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/Resources/Features",
    glue = "stepdefs"
)
public class testRunner extends AbstractTestNGCucumberTests{

}
