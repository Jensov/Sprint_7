import Order.CreateOrder;
import Order.OrderAssertions;
import Order.OrdersClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;


@RunWith(Parameterized.class)
public class CreateOrderTests {
    private List<String> color;

    public CreateOrderTests(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет: {0}")
    public static Object[][] colorGen() {
        return new Object[][]{
                {List.of("GRAY")},
                {List.of("GRAY", "BLACK")},
                {List.of("")},
        };
    }

    private final OrderAssertions check = new OrderAssertions();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Create Orders")
    @Description("Проверка создания заказа с одним цветом, двумя цветами, без указания цвета")
    public void CreateOrders() {
        OrdersClient ordersClient = new OrdersClient();
        ValidatableResponse createOrderResponse = ordersClient.createOrder(new CreateOrder("Иван", "Иванов", "Пушкина, дом Колотушкина", "Бульвар Рокоссовского", "+7 996 927 64 88", 5, "07-07-2023", "Как можно быстрее", color));
        check.createdSuccessfully(createOrderResponse);
    }
}