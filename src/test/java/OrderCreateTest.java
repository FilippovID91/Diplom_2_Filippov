
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static steps.StepsAccessToken.getUserToken;
import static steps.StepsOrder.*;
import static steps.StepsUserLogin.loginUser;
import static user_data.UserData.*;
import static user_and_order_pojo.OrderCreate.*;

public class OrderCreateTest {
    private String accessToken;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание заказа с ингридиентами и с accessToken - позитивный")
    @Description("Проверка создания заказа с ингридиентами и с accessToken - 200")
    public void createOrderSuccessTest() {
        Response response = loginUser(emailTestUser, passwordTestUser, nameTestUser);
        accessToken = getUserToken(response);
        Response responseOrder = createOrder(testOrder(), accessToken);
        checkOrderResponseWithIngredientsAndToken(responseOrder);
    }

    // Ошибка. Согласно ТЗ "Только авторизованные пользователи могут делать заказы". На самом деле через API удается создать заказ без авторизации.
    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание заказа с ингридиентами и без accessToken - негативный")
    @Description("Проверка создания заказа с ингридиентами и без accessToken - 401")
    public void createOrderWithoutTokenTest() {
        Response responseOrder = createOrderWithoutToken(testOrder());
        checkOrderResponseWithIngredientsWithoutToken(responseOrder);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание заказа без ингридиентов, но с accessToken - негативный")
    @Description("Проверка создания заказа без ингридиентов, но с accessToken  - 400")
    public void createOrderWithoutIngredientsTest() {
        Response response = loginUser(emailTestUser, passwordTestUser, nameTestUser);
        accessToken = getUserToken(response);
        Response responseOrder = createOrder(nullIngredientsOrder(), accessToken);
        checkOrderResponseWithoutToken(responseOrder);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание заказа с невалидными id ингридиентов, но с accessToken - негативный")
    @Description("Проверка создания заказа с невалидными id ингридиентов, но с accessToken  - 500")
    public void createOrderWithWrongIdIngredientsTest() {
        Response response = loginUser(emailTestUser, passwordTestUser, nameTestUser);
        accessToken = getUserToken(response);
        Response responseOrder = createOrder(wrongIdIngredientsOrder(), accessToken);
        checkOrderResponseWithWrongIdIngredients(responseOrder);
    }
}