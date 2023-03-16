package user_and_order_pojo;

import org.apache.commons.lang3.RandomStringUtils;

import static user_data.UserData.*;

public class UserRegistration {

    private String email;
    private String password;
    private String name;

    public UserRegistration(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static UserRegistration randomUser() {
        return new UserRegistration(RandomStringUtils.randomAlphabetic(4) + "@" + RandomStringUtils.randomAlphabetic(4) + ".com", RANDOM_PASS, RANDOM_NAME);
    }
}