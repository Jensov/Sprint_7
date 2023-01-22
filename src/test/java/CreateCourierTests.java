import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import Courier.*;

public class CreateCourierTests {
    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();

    private final CourierAssertions check = new CourierAssertions();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }


    @Test
    @DisplayName("Courier May Be Create")
    @Description("Проверка создания курьера с валидными данными")
    public void CourierMayBeCreate() {
        Courier courier = generator.random();
        ValidatableResponse createCourierResponse =
                client.createCourier(courier);
        check.createdSuccessfully(createCourierResponse);


        Id courierId = CourierClient.getCourierId(courier);
        ValidatableResponse deleteCourierResponse = CourierClient.deleteCourier(courierId.getId());
        check.deleteSuccessfully(deleteCourierResponse);
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


        Id courierId = CourierClient.getCourierId(courier);
        ValidatableResponse deleteCourierResponse = CourierClient.deleteCourier(courierId.getId());
        check.deleteSuccessfully(deleteCourierResponse);
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