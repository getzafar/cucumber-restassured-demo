package utils.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.restassured.response.Response;
import models.User;
import utils.ServiceManager;

public class UserServiceManager extends ServiceManager {

    private static final String endpoint = "users/";

    public static User[] getAllUsers() {
        Response response = getRequest(endpoint);
        return createUsersArrayFromJsonString(response.getBody().asString());
    }

    public static User getUserByUserId(int id) {
        Response response = getRequest(endpoint + id);
        return createUserFromJsonString(response.getBody().asString());
    }

    private static User[] createUsersArrayFromJsonString(String usersAsJsonString) {
        JsonArray jsonArray = new Gson().fromJson(usersAsJsonString, JsonArray.class);
        User[] users = new User[jsonArray.size()];
        int index = 0;
        for (JsonElement j : jsonArray) {
            users[index++] = new Gson().fromJson(j, User.class);
        }
        return users;
    }

    private static User createUserFromJsonString(String userAsJsonString) {
        return new Gson().fromJson(userAsJsonString, User.class);
    }
}
