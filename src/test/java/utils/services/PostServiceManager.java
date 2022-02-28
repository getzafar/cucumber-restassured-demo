package utils.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.restassured.response.Response;
import models.Comment;
import models.Post;
import utils.ServiceManager;

import java.util.Map;

public class PostServiceManager extends ServiceManager {
    private static final String endpoint = "posts/";

    public static Post[] getAllPosts() {
        Response response = getRequest(endpoint);
        return createPostsArrayFromJsonString(response.getBody().asString());
    }

    public static Post getPostByPostId(int id) {
        Response response = getRequest(endpoint + id);
        return createPostFromJsonString(response.getBody().asString());
    }

    public static Comment[] getCommentsOfAPost(int postId) {
        Response response = getRequest(endpoint + postId + "/comments");
        return CommentServiceManager.createCommentsArrayFromJsonString(response.getBody().asString());
    }

    public static Post postPost(Post post) {
        Response response = postRequest(endpoint, new Gson().toJson(post));
        return createPostFromJsonString(response.getBody().asString());
    }

    public static Post putPost(Post post) {
        Response response = putRequest(endpoint + post.getId(), new Gson().toJson(post));
        return createPostFromJsonString(response.getBody().asString());
    }

    public static void deletePost(Post post) {
        Response response = deleteRequest(endpoint + post.getId());
    }

    public static Post patchPostWithout(Post post, String elementToBeExcluded) {
        JsonElement jsonPayload = new Gson().toJsonTree(post);
        jsonPayload.getAsJsonObject().remove(elementToBeExcluded);

        Response response = patchRequest(endpoint + post.getId(), jsonPayload.toString());
        return createPostFromJsonString(response.getBody().asString());
    }

    private static Post[] createPostsArrayFromJsonString(String postsAsJsonString) {
        JsonArray jsonArray = new Gson().fromJson(postsAsJsonString, JsonArray.class);
        Post[] posts = new Post[jsonArray.size()];
        int index = 0;
        for (JsonElement j : jsonArray) {
            posts[index++] = new Gson().fromJson(j, Post.class);
        }
        return posts;
    }

    private static Post createPostFromJsonString(String postAsJsonString) {
        return new Gson().fromJson(postAsJsonString, Post.class);
    }

    public static Post createNewPost(Map<String, String> m) {

        Post p = Post.builder().userId(Integer.parseInt(m.get("userId")))
                .title(m.get("title"))
                .body(m.get("body"))
                .build();
        return postPost(p);
    }

    public static Post updateExistingPost(Post p, Map<String, String> m) {
        if (null != m.get("title"))
            p.setTitle(m.get("title"));
        if (null != m.get("body"))
            p.setBody(m.get("body"));

        return putPost(p);
    }

    public static void deleteExistingPost(Post p) {
        deletePost(p);
    }

    public static Post patchBodyOfAnExistingPost(Post p, String body) {
        p.setBody(body);
        return patchPostWithout(p, "title");
    }

    public static Post patchTitleOfAnExistingPost(Post p, String title) {
        p.setTitle(title);
        return patchPostWithout(p, "body");
    }
}
