
import org.testng.Assert;
import org.testng.annotations.Test;

import com.restassured.ApiConfig;

import io.restassured.response.Response;

public class UserApiTest extends BaseTest {

    @Test
    public void testGetAllUsers() {
        Response response = ApiConfig.makeGetRequest("/api/users?page=2", 200);
        Assert.assertNotNull(response.jsonPath().getList("data"));
    }

    @Test
    public void testGetOneUser() {
        Response response = ApiConfig.makeGetRequest("/api/users/2", 200);
        Integer id = response.jsonPath().getInt("data.id");
        Assert.assertNotNull(id);
    }

    @Test
    public void testGetUserNotFound() {
        Response response = ApiConfig.makeGetRequest("/api/users/23", 404);
        Assert.assertNull(response.jsonPath().getList("data"));
    }

}
