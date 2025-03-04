import org.testng.Assert;
import org.testng.annotations.Test;
import com.restassured.ApiConfig;
import io.restassured.response.Response;

public class UserApiTest extends BaseTest {


    private static final String TEST_USER_EMAIL = "test.user@reqres.in";
    private static final String TEST_USER_FIRST_NAME = "Test";
    private static final String TEST_USER_LAST_NAME = "User";
    private static final String TEST_USER_JOB = "Software Engineer";

    @Test(description = "Verify successful retrieval of paginated users")
    public void testGetAllUsers() {
        Response response = ApiConfig.makeGetRequest("/api/users?page=2", 200);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertNotNull(response.jsonPath().getList("data"), "User data list should not be null");
        Assert.assertFalse(response.jsonPath().getList("data").isEmpty(), "User list should not be empty");

        Assert.assertEquals(response.jsonPath().getInt("page"), 2, "Page number should be 2");
        Assert.assertNotNull(response.jsonPath().getInt("per_page"), "Per page count should not be null");
        Assert.assertNotNull(response.jsonPath().getInt("total"), "Total count should not be null");
        
        Assert.assertNotNull(response.jsonPath().getString("data[0].email"), "User email should not be null");
        Assert.assertNotNull(response.jsonPath().getString("data[0].first_name"), "User first name should not be null");
        Assert.assertNotNull(response.jsonPath().getString("data[0].last_name"), "User last name should not be null");
        Assert.assertNotNull(response.jsonPath().getString("data[0].avatar"), "User avatar should not be null");
    }

    @Test(description = "Verify successful retrieval of a single user")
    public void testGetOneUser() {
        Response response = ApiConfig.makeGetRequest("/api/users/2", 200);
        
        Assert.assertNotNull(response.jsonPath().getInt("data.id"), "User ID should not be null");
        Assert.assertNotNull(response.jsonPath().getString("data.email"), "User email should not be null");
        Assert.assertNotNull(response.jsonPath().getString("data.first_name"), "User first name should not be null");
        Assert.assertNotNull(response.jsonPath().getString("data.last_name"), "User last name should not be null");
    }

    @Test(description = "Verify 404 response for non-existent user")
    public void testGetUserNotFound() {
        Response response = ApiConfig.makeGetRequest("/api/users/23", 404);
        Assert.assertNull(response.jsonPath().getList("data"), "Data should be null for non-existent user");
    }

    @Test(description = "Verify successful creation of a new user")
    public void testCreateUser() {
        String requestBody = String.format(
            "{\"email\":\"%s\",\"first_name\":\"%s\",\"last_name\":\"%s\",\"job\":\"%s\"}",
            TEST_USER_EMAIL, TEST_USER_FIRST_NAME, TEST_USER_LAST_NAME, TEST_USER_JOB
        );

        Response response = ApiConfig.makePostRequest("/api/users", 201);
        
        Assert.assertNotNull(response.jsonPath().getString("id"), "Created user ID should not be null");
        Assert.assertEquals(response.jsonPath().getString("email"), TEST_USER_EMAIL, "Email should match");
        Assert.assertEquals(response.jsonPath().getString("first_name"), TEST_USER_FIRST_NAME, "First name should match");
        Assert.assertEquals(response.jsonPath().getString("last_name"), TEST_USER_LAST_NAME, "Last name should match");
        Assert.assertEquals(response.jsonPath().getString("job"), TEST_USER_JOB, "Job should match");
    }

    @Test(description = "Verify successful update of an existing user")
    public void testUpdateUser() {
        String updatedJob = "Senior Software Engineer";
        String requestBody = String.format("{\"job\":\"%s\"}", updatedJob);

        Response response = ApiConfig.makePutRequest("/api/users/2", 200);
        
        Assert.assertEquals(response.jsonPath().getString("job"), updatedJob, "Updated job should match");
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "Update timestamp should not be null");
    }

    @Test(description = "Verify successful deletion of a user")
    public void testDeleteUser() {
        Response response = ApiConfig.makeDeleteRequest("/api/users/2", 204);
        Assert.assertEquals(response.getStatusCode(), 204, "Status code should be 204 for successful deletion");
    }
}
