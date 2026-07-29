package user_and_order_pojo;

import static user_data.UserData.*;

public class UserLogin {

    private String email;
    private String password;
    private String name;

    public UserLogin(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
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

    public static UserLogin testUser() {
        return new UserLogin(emailTestUser, passwordTestUser, nameTestUser);
    }
}