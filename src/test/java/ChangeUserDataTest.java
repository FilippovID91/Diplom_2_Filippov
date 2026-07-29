import user_and_order_pojo.UserRegistration;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static steps.StepsAccessToken.getUserToken;
import static steps.StepsUserChange.*;
import static steps.StepsUserDelete.deleteUser;
import static steps.StepsUserRegistration.registrationUser;
import static user_and_order_pojo.UserRegistration.randomUser;

public class ChangeUserDataTest {
    private UserRegistration randomUser;
    private Response response;
    private Response responseChangeUser;
    private String accessToken;
    private String email;
    private String password;
    private String name;

    @Before
    public void setup() {
        randomUser = randomUser();
        email = randomUser.getEmail();
        password = randomUser.getPassword();
        name = randomUser.getName();
        response = registrationUser(email, password, name);
    }

    @After
    public void teardown() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение email и name пользователя после авторизации - позитивный")
    @Description("Проверка изменения email и name пользователя после авторизации - 200")
    public void changeEmailUserSuccessTest() {
        randomUser.setEmail(email + "Test");
        randomUser.setName(name + "Test");
        accessToken = getUserToken(response);
        responseChangeUser = changeUser(randomUser, accessToken);
        checkChangeUserProfile(responseChangeUser);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение password пользователя после авторизации - позитивный")
    @Description("Проверка изменения password пользователя после авторизации - 200")
    public void changePasswordUserSuccessTest() {
        randomUser.setPassword(password + "Test");
        accessToken = getUserToken(response);
        responseChangeUser = changeUser(randomUser, accessToken);
        checkChangeUserProfile(responseChangeUser);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение email, name пользователя и невалидной авторизации - негативный")
    @Description("Проверка изменения email, name пользователя и невалидной авторизации - 401")
    public void changeEmailUserWithWrongTokenTest() {
        randomUser.setEmail(email + "Test");
        randomUser.setName(name + "Test");
        responseChangeUser = changeUser(randomUser, "test");
        checkNotChangedUserProfile(responseChangeUser);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение password пользователя и невалидной авторизации - негативный")
    @Description("Проверка изменения password пользователя и невалидной авторизации - 401")
    public void changePasswordUserWithWrongTokenTest() {
        randomUser.setPassword(password + "Test");
        responseChangeUser = changeUser(randomUser, "test");
        checkNotChangedUserProfile(responseChangeUser);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение email и name пользователя без авторизации - негативный")
    @Description("Проверка изменения email и name пользователя без авторизации - 401")
    public void changeEmailUserWithoutTokenTest() {
        randomUser.setEmail(email + "Test");
        randomUser.setName(name + "Test");
        responseChangeUser = changeUserWithoutToken(randomUser);
        checkNotChangedUserProfile(responseChangeUser);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение password пользователя без авторизации - негативный")
    @Description("Проверка изменения password пользователя без авторизации - 401")
    public void changePasswordUserWithoutTokenTest() {
        randomUser.setPassword(password + "Test");
        responseChangeUser = changeUserWithoutToken(randomUser);
        checkNotChangedUserProfile(responseChangeUser);
    }
}