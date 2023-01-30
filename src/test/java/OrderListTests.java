import Order.OrdersClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;


public class OrderListTests extends BaseTest {

    @Test
    @DisplayName("Check Orders List")
    @Description("Проверка получения списка заказов")
    public void checkOrdersList() {
        OrdersClient ordersClient = new OrdersClient();
        ordersClient.checkOrderList();
    }
}
