package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseHttpClient {
    public static String BASE_URI = "https://stellarburgers.nomoreparties.site/api";
    public static String USER = "/auth/user";
    public static String ORDERS = "/orders";
    public static String REGISTER = "/auth/register";
    public static String LOGIN = "/auth/login";

    protected static Response doGetRequest (String uri) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .get(uri);
    }

    protected static Response doGetRequestWithToken(String uri, String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .baseUri(BASE_URI)
                .when()
                .get(uri);
    }

    protected static Response doPostRequestWithObject (String uri, Object object) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(object)
                .when()
                .post(uri);
    }

    protected static Response doPostRequestWithObjectAndToken (String uri, Object object, String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .baseUri(BASE_URI)
                .body(object)
                .when()
                .post(uri);
    }

    protected static Response doPatchRequestWithToken (String uri, Object object, String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .baseUri(BASE_URI)
                .body(object)
                .when()
                .patch(uri);
    }

    protected static Response doPatchRequestWithoutToken (String uri, Object object) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(object)
                .when()
                .patch(uri);
    }

    protected static Response doDeleteRequest (String uri, String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .baseUri(BASE_URI)
                .when()
                .delete(uri);
    }
}