package steps;

import client.BaseHttpClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;

public class StepsUserChange extends BaseHttpClient {

    // PATCH /auth/user
    @Step("Изменение профиля пользователя")
    public static Response changeUser(Object changeUser, String accessToken) {
        return doPatchRequestWithToken(USER, changeUser, accessToken);
    }

    // PATCH /auth/user
    @Step("Изменение профиля пользователя")
    public static Response changeUserWithoutToken(Object changeUser) {
        return doPatchRequestWithoutToken(USER, changeUser);
    }

    // PATCH /auth/user - checking
    @Step("Проверка тела и статуса ответа при изменении профиля пользователя после авторизации - 200")
    public static void checkChangeUserProfile(Response response) {
        response.then()
                .body("success", equalTo(true))
                .and()
                .statusCode(200);
    }

    // PATCH /auth/user - checking
    @Step("Проверка тела и статуса ответа при изменении профиля пользователя без авторизации - 401")
    public static void checkNotChangedUserProfile(Response response) {
        response.then()
                .body("success", equalTo(false))
                .and()
                .statusCode(401);
    }
}