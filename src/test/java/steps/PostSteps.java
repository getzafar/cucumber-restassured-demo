package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import models.Comment;
import models.Post;
import utils.services.PostServiceManager;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class PostSteps {

    private static Post[] posts;
    private static Comment[] comments;
    private static Post post;

    @Before
    public static void beforeEveryScenarioOfUserSteps() {
        posts = null;
        post = null;
        comments = null;
    }

    @After
    public static void afterEveryScenarioOfUserSteps() {
    }

    @Given("^all the posts are queried from Posts service$")
    public void queryAllPosts() {
        posts = PostServiceManager.getAllPosts();
    }

    @Given("^a post is queried by id \"([^\"]*)\"$")
    public void queryPostById(int id) {
        post = PostServiceManager.getPostByPostId(id);
    }

    @Given("^all the comments are queried from Posts service by post id \"([^\"]*)\"$")
    public void queryAllCommentsPostById(int postId) {
        comments = PostServiceManager.getCommentsOfAPost(postId);
    }

    @Given("^a new post is created$")
    public void createAPost(Map<String, String> newPostMap) {
        post = PostServiceManager.createNewPost(newPostMap);
    }

    @Given("^this post is updated with below details$")
    public void updateAnExistingPost(Map<String, String> newPostMap) {
        post = PostServiceManager.updateExistingPost(post, newPostMap);
    }

    @Given("^this post is deleted$")
    public void deleteAPost() {
        PostServiceManager.deleteExistingPost(post);
    }

    @Given("^\"([^\"]*)\" of this post is patched as \"([^\"]*)\"$")
    public void patchAnExistingPost(String elementToBePatched, String patchValue) {
        if (elementToBePatched.equals("body"))
            post = PostServiceManager.patchBodyOfAnExistingPost(post, patchValue);
        else if (elementToBePatched.equals("title"))
            post = PostServiceManager.patchTitleOfAnExistingPost(post, patchValue);
    }

    @Then("^there exist one or more posts$")
    public void oneOrMorePostsExist() {
        assertTrue("At least one post exists", posts.length > 0);
    }

    @Then("^the id of queried post is \"([^\"]*)\"$")
    public void verifyIdOfPost(int id) {
        assertTrue("Id is correct", post.getId() == id);
    }

    @Then("^the userid of queried post is \"([^\"]*)\"$")
    public void verifyUsernameOfUser(int userId) {
        assertTrue("userid is correct", post.getUserId() == userId);
    }

    @Then("^there exist one or more comments for the post$")
    public void oneOrMoreCommentsExistForThePost() {
        assertTrue("At least one comment exists", comments.length > 0);
    }

    @Then("^the id of newly created post is \"([^\"]*)\"$")
    public void verifyIdOfNewlyCreatedPost(int postId) {
        assertTrue("Id of new post is correct", post.getId() == postId);
    }
}
