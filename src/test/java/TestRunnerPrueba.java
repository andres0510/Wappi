import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/main/resources/features",
    glue = {"com/wappi/test/steps/"},
    plugin = {"pretty", "html:target/cucumber", "json:target/prueba.json"}
)
public class TestRunnerPrueba {
}
