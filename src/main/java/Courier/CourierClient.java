package Courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class CourierClient {
    public final static String ROOT = "/api/v1/courier/";

    // можно сделать рефакторинг адресов запросов - ручек
    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when().post(ROOT)
                .then().log().all();
    }

    @Step("Авторизация в личном кабинете курьера")
    public ValidatableResponse loginCourier(Courier loginCourier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(loginCourier)
                .when()
                .post(ROOT +"login")
                .then().log().all();
    }

    @Step("Получение ID курьера")
    public static Id getCourierId(Courier loginCourier) {
        return given()
                .header("Content-type", "application/json")
                .body(loginCourier)
                .post(ROOT +"login")
                .body()
                .as(Id.class);
    }

    @Step("Удаление курьера по ID")
    public static ValidatableResponse deleteCourier(int courierId) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body("{\"id\": " + courierId + "}")
                .when()
                .delete(ROOT + courierId)
                .then().log().all();


    }
}
