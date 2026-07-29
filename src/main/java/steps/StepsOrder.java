package steps;

import client.BaseHttpClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StepsOrder extends BaseHttpClient {

    // POST /orders
    @Step("Создание заказа")
    public static Response createOrder(Object ingredients, String accessToken) {
        return doPostRequestWithObjectAndToken(ORDERS, ingredients, accessToken);
    }

    @Step("Создание заказа без accessToken")
    public static Response createOrderWithoutToken(Object ingredients) {
        return doPostRequestWithObject(ORDERS, ingredients);
    }

    // POST /orders - checking
    @Step("Проверка тела и статуса ответа при создании заказа с ингридиентами и с accessToken - 200")
    public static void checkOrderResponseWithIngredientsAndToken(Response response) {
        response.then()
                .body("success", equalTo(true))
                .and()
                .statusCode(200);
    }

    // POST /orders - checking
    @Step("Проверка тела и статуса ответа при создании заказа с ингридиентами и без accessToken - 401")
    public static void checkOrderResponseWithIngredientsWithoutToken(Response response) {
        response.then()
                .body("success", equalTo(true))
                .and()
                .statusCode(200);
    }

    // POST /orders - checking
    @Step("Проверка тела и статуса ответа при создании заказа без ингридиентов, но с accessToken - 400")
    public static void checkOrderResponseWithoutToken(Response response) {
        response.then()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Ingredient ids must be provided"))
                .and()
                .statusCode(400);
    }

    // POST /orders - checking
    @Step("Проверка тела и статуса ответа при создании заказа c невалидным id ингридиентов - 500")
    public static void checkOrderResponseWithWrongIdIngredients(Response response) {
        response.then()
                .statusCode(500);
    }

    // GET /orders
    @Step("Запрос данных о заказах пользователя по accessToken")
    public static Response getUserOrders(String accessToken) {
        return doGetRequestWithToken(ORDERS, accessToken);
    }

    // GET /orders - checking
    @Step("Проверка тела и статуса ответа при запросе заказов пользователя - 200")
    public static void checkGetOrderResponse(Response response) {
        response.then()
                .body("orders", notNullValue())
                .and()
                .statusCode(200);
    }

    // GET /orders
    @Step("Запрос данных о заказах пользователя без accessToken")
    public static Response getUserOrdersWithoutToken() {
        return doGetRequest(ORDERS);
    }

    // GET /orders - checking
    @Step("Проверка тела и статуса ответа при запросе заказов пользователя без авторизации - 401")
    public static void checkGetOrderResponseWithoutToken(Response response) {
        response.then()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"))
                .and()
                .statusCode(401);
    }
}