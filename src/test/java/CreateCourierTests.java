import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import Courier.*;

public class CreateCourierTests extends BaseTest {
    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();

    private final CourierAssertions check = new CourierAssertions();
    private int id;

    @After
    public void deleteCourier() {
        CourierClient.deleteCourier(id);
    }

    @Test
    @DisplayName("Courier May Be Create")
    @Description("Проверка создания курьера с валидными данными")
    public void CourierMayBeCreate() {
        Courier courier = generator.random();
        ValidatableResponse createCourierResponse =
                client.createCourier(courier);
        check.createdSuccessfully(createCourierResponse);


        id = CourierClient.getCourierId(courier).getId();
    }

    @Test
    @DisplayName("Check Create Duplicate Courier")
    @Description("Нельзя создать двух одинаковых курьеров")
    public void checkCreateDuplicateCourier() {
        Courier courier = generator.random();
        ValidatableResponse createCourierResponse =
                client.createCourier(courier);
        check.createdSuccessfully(createCourierResponse);

        courier.getLogin();
        ValidatableResponse createDuplicateCourierResponse =
                client.createCourier(courier);
        check.loginIsDuplicate(createDuplicateCourierResponse);

        id = CourierClient.getCourierId(courier).getId();
    }

    @Test
    @DisplayName("Create Null Password Courier Test")
    @Description("Проверка создания курьера без пароля")
    public void createNullPasswordCourierTest() {
        Courier courier = generator.random();
        courier.setPassword(null);
        ValidatableResponse createNullPasswordFieldResponse = client.createCourier(courier);
        check.createdFailed(createNullPasswordFieldResponse);
    }

    @Test
    @DisplayName("Create Null Login Courier Test")
    @Description("Проверка создания курьера без логина")
    public void createNullLoginCourierTest() {
        Courier courier = generator.random();
        courier.setLogin(null);
        ValidatableResponse createNullLoginFieldResponse = client.createCourier(courier);
        check.createdFailed(createNullLoginFieldResponse);
    }

}