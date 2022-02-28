package utils.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.restassured.response.Response;
import models.Comment;
import utils.ServiceManager;

import java.util.HashMap;
import java.util.Map;

public class CommentServiceManager extends ServiceManager {

    private static final String endpoint = "comments/";

    public static Comment[] getAllComments() {
        Response response = getRequest(endpoint);
        return createCommentsArrayFromJsonString(response.getBody().asString());
    }

    public static Comment getCommentsByCommentId(int id) {
        Response response = getRequest(endpoint + id);
        return createCommentFromJsonString(response.getBody().asString());
    }

    public static Comment[] getAllCommentsByPostId(int postId) {
        Map queryParam = new HashMap();
        queryParam.put("postId", postId);
        Response response = getRequest(endpoint, queryParam);
        return createCommentsArrayFromJsonString(response.getBody().asString());
    }

    protected static Comment[] createCommentsArrayFromJsonString(String commentsAsJsonString) {
        JsonArray jsonArray = new Gson().fromJson(commentsAsJsonString, JsonArray.class);
        Comment[] comments = new Comment[jsonArray.size()];
        int index = 0;
        for (JsonElement j : jsonArray) {
            comments[index++] = new Gson().fromJson(j, Comment.class);
        }
        return comments;
    }

    private static Comment createCommentFromJsonString(String commentAsJsonString) {
        return new Gson().fromJson(commentAsJsonString, Comment.class);
    }
}
