package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import utils.ServiceManager;

import static org.junit.Assert.assertTrue;

public class CommonSteps {

    @Before
    public static void beforeEveryScenarioOfCommonSteps() {
    }

    @After
    public static void afterEveryScenarioOfCommonSteps() {
    }

    @Then("^the service response is \"([^\"]*)\"$")
    public void usersServiceResponds(int expectedStatus) {
        assertTrue("Service response is " + expectedStatus, ServiceManager.verifyResponseCode(expectedStatus));
    }

}
