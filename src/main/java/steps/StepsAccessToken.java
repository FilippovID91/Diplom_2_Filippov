package steps;

import client.BaseHttpClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class StepsAccessToken extends BaseHttpClient {

    @Step("Получение токена авторизации пользователя")
    public static String getUserToken(Response response) {
        return response.
                then().
                extract().
                body().
                path("accessToken");
    }
}