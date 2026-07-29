package user_data;

import org.apache.commons.lang3.RandomStringUtils;

public class UserData {
    public static String RANDOM_EMAIL = RandomStringUtils.randomAlphabetic(4) + "@" + RandomStringUtils.randomAlphabetic(4) + ".com";
    public static String RANDOM_PASS = RandomStringUtils.randomNumeric(6);
    public static String RANDOM_NAME = RandomStringUtils.randomAlphabetic(6);

    // Тестовый зарегистрированный пользователь
    public static String emailTestUser = "filippovTest@gmail.com";
    public static String passwordTestUser = "470956857";
    public static String nameTestUser = "Ivan";
}