package steps;

import client.BaseHttpClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;

public class StepsUserDelete extends BaseHttpClient {

    //  DELETE /auth/user
    @Step("Удаление курьера по его токену")
    public static void deleteUser(String accessToken) {
        Response responseDelete = doDeleteRequest(USER, accessToken);
        responseDelete.then()
                .body("success", equalTo(true))
                .and()
                .body("message", equalTo("User successfully removed"))
                .and()
                .statusCode(202);
    }
}