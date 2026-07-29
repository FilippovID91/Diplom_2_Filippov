import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import user_and_order_pojo.UserRegistration;

import static steps.StepsAccessToken.getUserToken;
import static steps.StepsUserDelete.deleteUser;
import static steps.StepsUserRegistration.*;
import static user_and_order_pojo.UserRegistration.randomUser;
import static user_data.UserData.*;

public class UserRegistrationTest {
    private String accessToken;

    @After
    public void teardown() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание пользователя - позитивный")
    @Description("Создание пользователя по валидному логину, паролю и имени - 200")
    public void createUserSuccessTest() {
        UserRegistration randomUser = randomUser();
        Response response = registrationUser(randomUser.getEmail(), randomUser.getPassword(), randomUser.getName());
        checkRegistrationResponseWithValidData(response);
        accessToken = getUserToken(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание двух одинаковых пользователей - негативный")
    @Description("Проверка запрета создания двух пользователей с одинаковыми email - 409")
    public void createTheSameUserAgainReturnErrorTest() {
        Response response = registrationUser(emailTestUser, passwordTestUser, nameTestUser);
        checkResponseWithAgainRegistration(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание пользователя с незаполненным email - негативный")
    @Description("Проверка обязательности заполнения email при регистрации - 403")
    public void createUserWithoutEmailTest() {
        Response response = registrationUser(null, RANDOM_PASS, RANDOM_NAME);
        checkRegistrationResponseWithNotEnoughData(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание пользователя с незаполненным password - негативный")
    @Description("Проверка обязательности заполнения password при регистрации - 403")
    public void createUserWithoutPasswordTest() {
        Response response = registrationUser(RANDOM_EMAIL, null, RANDOM_NAME);
        checkRegistrationResponseWithNotEnoughData(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание пользователя с незаполненным name - негативный")
    @Description("Проверка обязательности заполнения name при регистрации - 403")
    public void createUserWithoutNameTest() {
        Response response = registrationUser(RANDOM_EMAIL, RANDOM_PASS, null);
        checkRegistrationResponseWithNotEnoughData(response);
    }
}