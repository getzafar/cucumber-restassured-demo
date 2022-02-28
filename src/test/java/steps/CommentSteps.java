package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import models.Comment;
import utils.services.CommentServiceManager;

import static org.junit.Assert.assertTrue;

public class CommentSteps {

    private static Comment[] comments;
    private static Comment comment;

    @Before
    public static void beforeEveryScenarioOfUserSteps() {
        comments = null;
        comment = null;
    }

    @After
    public static void afterEveryScenarioOfUserSteps() {
    }

    @Given("^all the comments are queried from Comments service$")
    public void queryAllComments() {
        comments = CommentServiceManager.getAllComments();
    }

    @Given("^a comment is queried by id \"([^\"]*)\"$")
    public void queryCommentById(int id) {
        comment = CommentServiceManager.getCommentsByCommentId(id);
    }

    @Given("^all the comments are queried from Comments service for postId \"([^\"]*)\"$")
    public void queryCommentsByPostId(int postId) {
        comments = CommentServiceManager.getAllCommentsByPostId(postId);
    }

    @Then("^there exist one or more comments$")
    public void oneOrMoreCommentsExist() {
        assertTrue("At least one comment exists", comments.length > 0);
    }

    @Then("^there exist \"([^\"]*)\" comments$")
    public void oneOrMoreCommentsExist(int commentCout) {
        assertTrue("Given number of comments exist", comments.length == commentCout);
    }

    @Then("^the id of queried comment is \"([^\"]*)\"$")
    public void verifyIdOfComment(int id) {
        assertTrue("Id is correct", comment.getId() == id);
    }

    @Then("^the postId of queried comment is \"([^\"]*)\"$")
    public void verifyPostIdOfComment(int postId) {
        assertTrue("postId is correct", comment.getPostId() == postId);
    }

    @Then("^the email of queried comment is \"([^\"]*)\"$")
    public void verifyEmailOfComment(String email) {
        assertTrue("email is correct", comment.getEmail().equals(email));
    }
}
