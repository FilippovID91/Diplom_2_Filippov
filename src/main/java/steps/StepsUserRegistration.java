package steps;

import client.BaseHttpClient;
import user_and_order_pojo.UserRegistration;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;

public class StepsUserRegistration extends BaseHttpClient {

    // POST /auth/register
    @Step("Регистрация нового пользователя с валидными данными")
    public static Response registrationUser(String email, String password, String name) {
        UserRegistration userRegistration = new UserRegistration(email, password, name);
        return doPostRequestWithObject(REGISTER, userRegistration);
    }

    // POST /auth/register - checking
    @Step("Проверка тела и статуса ответа при первой валидной регистрации - 200")
    public static void checkRegistrationResponseWithValidData(Response response) {
        response.then()
                .body("success", equalTo(true))
                .and()
                .statusCode(200);
    }

    // POST /auth/register - checking
    @Step("Проверка тела и статуса ответа при попытке повторной регистрации - 403")
    public static void checkResponseWithAgainRegistration(Response response) {
        response.then()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("User already exists"))
                .and()
                .statusCode(403);
    }

    // POST /auth/register - checking
    @Step("Проверка тела и статуса ответа при незаполненных email/password/name - 403")
    public static void checkRegistrationResponseWithNotEnoughData(Response response) {
        response.then()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Email, password and name are required fields"))
                .and()
                .statusCode(403);
    }
}