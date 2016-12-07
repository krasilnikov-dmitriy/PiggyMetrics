package com.piggymetrics.account;

import com.jayway.restassured.RestAssured;
import static org.hamcrest.core.Is.is;
import com.piggymetrics.emulators.AuthServiceEmulatorClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jayway.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;

/**
 * Created by dmitry on 12/7/16.
 */
public class AccountServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private AuthServiceEmulatorClient authServiceClient = new AuthServiceEmulatorClient();

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getenv("ACCOUNT_SERVICE_HOST"); //"http://account-service";
        RestAssured.port = Integer.parseInt(System.getenv("ACCOUNT_SERVICE_PORT"));
        RestAssured.basePath = System.getenv("ACCOUNT_SERVICE_BASE_PATH");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Title("Unauthorized users could't get current user info")
    @Test
    public void unauthorizedUsersShouldNotGetAccountInformation() {

        logInfo("Mock auth service for return unauthorized response");
        authServiceClient.mockUnauthorizedResponse(null);

        logInfo("Get current user account");
        given()
        .when()
            .get("/admin")
        .then()
            .assertThat().statusCode(HTTP_UNAUTHORIZED)
            .assertThat().body("error", is("unauthorized"));

    }

    @Title("Authorized users could get current user info")
    @Test
    public void authorizedUsersShouldGetAccountInformation() {

        logInfo("Mock auth service for return authorized response");
        authServiceClient.mockAuthorizedResponse(null);

        logInfo("Get current user account");
        given()
//            .auth().oauth2("SOME_TOKEN")
        .when()
            .get("/demo")
        .then()
            .statusCode(HTTP_OK);

    }

    @Step("{0}")
    private void logInfo(String s) {
        log.info(s);
    }
}
