import Order.OrdersClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;


public class OrderListTests {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Check Orders List")
    @Description("Проверка получения списка заказов")
    public void checkOrdersList() {
        OrdersClient ordersClient = new OrdersClient();
        ordersClient.checkOrderList();
    }
}
