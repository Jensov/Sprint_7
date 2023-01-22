package Order;

import OrderList.OrdersList;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrdersClient {
    @Step("Создание заказа")
    public ValidatableResponse createOrder(CreateOrder createOrder) {
        return given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(createOrder)
                .when()
                .post("/api/v1/orders")
                .then().log().all();
    }

    @Step("Получение списка заказов")
    public OrdersList checkOrderList() {
        return given().then().log().all()
                .header("Content-type", "application/json; charset=utf-8")
                .when()
                .get("/api/v1/orders")
                .body()
                .as(OrdersList.class);
    }
}