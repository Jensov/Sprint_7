package Courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient {
    // можно сделать рефакторинг адресов запросов - ручек
    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when().post("/api/v1/courier")
                .then().log().all();
    }

    @Step("Авторизация в личном кабинете курьера")
    public ValidatableResponse loginCourier(Courier loginCourier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(loginCourier)
                .when()
                .post("/api/v1/courier/login")
                .then().log().all();
    }

    @Step("Получение ID курьера")
    public static Id getCourierId(Courier loginCourier) {
        return given()
                .header("Content-type", "application/json")
                .body(loginCourier)
                .post("/api/v1/courier/login")
                .body()
                .as(Id.class);
    }

    @Step("Удаление курьера по ID")
    public static ValidatableResponse deleteCourier(int courierId) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body("{\"id\": " + courierId + "}")
                .when()
                .delete("/api/v1/courier/" + courierId)
                .then().log().all();


    }
}
