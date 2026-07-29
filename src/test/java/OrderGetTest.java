import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import user_and_order_pojo.UserRegistration;

import static steps.StepsAccessToken.getUserToken;
import static steps.StepsOrder.*;
import static steps.StepsUserDelete.deleteUser;
import static steps.StepsUserRegistration.*;
import static user_and_order_pojo.OrderCreate.testOrder;
import static user_and_order_pojo.UserRegistration.randomUser;

public class OrderGetTest {
    private Response response;
    private Response responseOrder;
    private String accessToken;

    @After
    public void teardown() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Запрос данных о заказах пользователя c accessToken - позитивный")
    @Description("Проверка запроса заказа с accessToken - 200")
    public void getUserOrdersTest() {
        UserRegistration randomUser = randomUser();
        response = registrationUser(randomUser.getEmail(), randomUser.getPassword(), randomUser.getName());
        accessToken = getUserToken(response);
        response = createOrder(testOrder(), accessToken);
        response = getUserOrders(accessToken);
        checkGetOrderResponse(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Запрос данных о заказах пользователя без accessToken - негативный")
    @Description("Проверка запроса заказа без accessToken - 401")
    public void getUserOrdersWithoutTokenTest() {
        responseOrder= getUserOrdersWithoutToken();
        checkGetOrderResponseWithoutToken(responseOrder);
    }
}