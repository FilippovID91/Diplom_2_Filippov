package steps;

import client.BaseHttpClient;
import user_and_order_pojo.UserLogin;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;

public class StepsUserLogin extends BaseHttpClient {

    // POST /auth/login
    @Step("Авторизация пользователя с валидными данными")
    public static Response loginUser(String email, String password, String name) {
        UserLogin userLogin = new UserLogin(email, password, name);
        return doPostRequestWithObject(LOGIN, userLogin);
    }

    // POST /auth/login - checking
    @Step("Проверка тела и статуса ответа при валидных email/password - 200")
    public static void checkLoginResponseWithValidData(Response response) {
        response.then()
                .body("success", equalTo(true))
                .and()
                .statusCode(200);
    }

    // POST /auth/login - checking
    @Step("Проверка тела и статуса ответа при неверных email/password - 401")
    public static void checkLoginResponseWithWrongData(Response response) {
        response.then()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"))
                .and()
                .statusCode(401);
    }
}