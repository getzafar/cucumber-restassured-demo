package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import models.User;
import utils.services.UserServiceManager;

import static org.junit.Assert.assertTrue;

public class UserSteps {

    private static User[] users;
    private static User user;

    @Before
    public static void beforeEveryScenarioOfUserSteps() {
        users = null;
        user = null;
    }

    @After
    public static void afterEveryScenarioOfUserSteps() {
    }

    @Given("^all the users are queried from Users service$")
    public void queryAllUsers() {
        users = UserServiceManager.getAllUsers();
    }

    @Given("^a user is queried by id \"([^\"]*)\"$")
    public void queryUserById(int id) {
        user = UserServiceManager.getUserByUserId(id);
    }

    @Then("^there exist one or more users$")
    public void oneOrMoreUsersExist() {
        assertTrue("At least one user exists", users.length > 0);
    }

    @Then("^the id of queried user is \"([^\"]*)\"$")
    public void verifyIdOfUser(int id) {
        assertTrue("Id is correct", user.getId() == id);
    }

    @Then("^the username of queried user is \"([^\"]*)\"$")
    public void verifyUsernameOfUser(String username) {
        assertTrue("username is correct", user.getUsername().equals(username));
    }
}
