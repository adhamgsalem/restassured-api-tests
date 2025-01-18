
import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.restassured.ApiConfig;

import io.restassured.RestAssured;

public class BaseTest {

    @BeforeTest
    public void beforeTest() {
        RestAssured.baseURI = ApiConfig.BASE_URI;
    }

    @AfterTest
    public void afterTest() {
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        String methodName = method.getName();
        System.out.println("Test method name is: " + methodName);
    }

    @AfterMethod
    public void AfterMethod() {
    }
}
