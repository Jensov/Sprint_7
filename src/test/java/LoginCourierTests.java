import Courier.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTests {
    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();

    private final CourierAssertions check = new CourierAssertions();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Courier May Be Logged In")
    @Description("Проверка авторизации с валидными данными")
    public void CourierMayBeLoggedIn() {
        Courier courier = generator.random();
        ValidatableResponse createCourierResponse =
                client.createCourier(courier);
        check.createdSuccessfully(createCourierResponse);

        ValidatableResponse loginCourierResponse = client.loginCourier(courier);
        check.loggedInSuccessfully(loginCourierResponse);

        Id courierId = CourierClient.getCourierId(courier);
        ValidatableResponse deleteCourierResponse = CourierClient.deleteCourier(courierId.getId());
        check.deleteSuccessfully(deleteCourierResponse);
    }

    @Test
    @DisplayName("Sign In Without Login")
    @Description("Проверка авторизации без логина")
    public void sighInWithoutLogin() {
        Courier courier = generator.random();
        courier.setLogin(null);
        ValidatableResponse loginCourierResponse = client.loginCourier(courier);
        check.loginFailed(loginCourierResponse);
    }

    @Test
    @DisplayName("Sign In Without Password")
    @Description("Проверка авторизации без пароля")
    public void signInWithoutPassword() {
        Courier courier = generator.random();
        courier.setPassword(null);
        ValidatableResponse loginCourierResponse = client.loginCourier(courier);
        check.loginFailed(loginCourierResponse);
    }

    @Test
    @DisplayName("Sign In With Non Existent Login")
    @Description("Проверка авторизации с несуществующим логином")
    public void signInWithNonExistentLogin() {
        Courier courier = generator.random();

        courier.setLogin("12345");
        ValidatableResponse loginCourierResponse = client.loginCourier(courier);
        check.loginNonExistent(loginCourierResponse);
    }

    @Test
    @DisplayName("Sign In With Non Existent Password")
    @Description("Проверка авторизации с несуществующим паролем")
    public void signInWithNonExistentPassword() {
        Courier courier = generator.random();

        courier.setPassword("12345");
        ValidatableResponse loginCourierResponse = client.loginCourier(courier);
        check.loginNonExistent(loginCourierResponse);
    }
}
