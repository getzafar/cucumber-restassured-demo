import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.ServiceManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty","html:target/reports/test-report"},
        tags = "@user,@post,@comment"
)
public class RunCukesTest {

    @BeforeClass
    public static void prepareTestSuite() {
        ServiceManager.setupServiceManager();
    }

    @AfterClass
    public static void tearDownTestSuite() {

    }
}
