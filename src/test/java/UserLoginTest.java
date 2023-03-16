import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static steps.StepsUserLogin.*;
import static user_data.UserData.*;

public class UserLoginTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Авторизация пользователя - позитивный")
    @Description("Авторизация существующего пользователя по валидному логину и паролю - 200")
    public void loginUserSuccessTest() {
        Response response = loginUser(emailTestUser, passwordTestUser, nameTestUser);
        checkLoginResponseWithValidData(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Авторизация пользователя с невалидным email/password - негативный")
    @Description("Проверка запрета авторизации пользователя с невалидными email/password - 401")
    public void loginUserWithWrongEmailAndPasswordTest() {
        Response response = loginUser("RANDOM_EMAIL", "RANDOM_PASS", "RANDOM_NAME");
        checkLoginResponseWithWrongData(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Авторизация пользователя с валидным email и невалидным password - негативный")
    @Description("Проверка запрета авторизации пользователя с валидным email и не валидным password - 401")
    public void loginUserWithWrongPasswordTest() {
        Response response = loginUser(emailTestUser, RANDOM_PASS, RANDOM_NAME);
        checkLoginResponseWithWrongData(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Авторизация пользователя с невалидным email и валидным password - негативный")
    @Description("Проверка запрета авторизации пользователя с невалидным email и валидным password - 401")
    public void loginUserWithWrongEmailTest() {
        Response response = loginUser(RANDOM_EMAIL, passwordTestUser, RANDOM_NAME);
        checkLoginResponseWithWrongData(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Авторизация пользователя с незаполненным email - негативный")
    @Description("Проверка обязательности заполнения email при регистрации - 401")
    public void createUserWithoutEmailTest() {
        Response response = loginUser(null, passwordTestUser, RANDOM_NAME);
        checkLoginResponseWithWrongData(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Авторизация пользователя с незаполненным password - негативный")
    @Description("Проверка обязательности заполнения password при регистрации -  - 401")
    public void createUserWithoutPasswordTest() {
        Response response = loginUser(emailTestUser, null, RANDOM_NAME);
        checkLoginResponseWithWrongData(response);
    }
}