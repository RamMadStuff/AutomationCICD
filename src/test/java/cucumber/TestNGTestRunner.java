package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/cucumber",
    glue = "SeliniumMaven.stepDefinationImpl",tags="@Regression",
    monochrome = true,
    plugin = {"html:target/cucumber-report.html"}
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
