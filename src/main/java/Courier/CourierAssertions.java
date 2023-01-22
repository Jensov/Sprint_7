package Courier;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class CourierAssertions {
    public void createdSuccessfully(ValidatableResponse response) {
        response.assertThat().statusCode(201).body("ok", is(true));
    }

    public void loggedInSuccessfully(ValidatableResponse response) {
        response.statusCode(200).assertThat().body("id", notNullValue());
    }

    public void createdFailed(ValidatableResponse response) {
        response.statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    public void deleteSuccessfully(ValidatableResponse response) {
        response.statusCode(200).assertThat().body("ok", equalTo(true));
    }

    public void loginFailed(ValidatableResponse response) {
        response.statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    public void loginNonExistent(ValidatableResponse response) {
       response.statusCode(404).assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    public void loginIsDuplicate(ValidatableResponse response) {
        response.statusCode(409).assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}
