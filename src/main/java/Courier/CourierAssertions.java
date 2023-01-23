package Courier;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;
import static org.apache.http.HttpStatus.*;

public class CourierAssertions {
    public void createdSuccessfully(ValidatableResponse response) {
        response.assertThat().statusCode(SC_CREATED).body("ok", is(true));
    }

    public void loggedInSuccessfully(ValidatableResponse response) {
        response.statusCode(SC_OK).assertThat().body("id", notNullValue());
    }

    public void createdFailed(ValidatableResponse response) {
        response.statusCode(SC_BAD_REQUEST).assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    public void deleteSuccessfully(ValidatableResponse response) {
        response.statusCode(SC_OK).assertThat().body("ok", equalTo(true));
    }

    public void loginFailed(ValidatableResponse response) {
        response.statusCode(SC_BAD_REQUEST).assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    public void loginNonExistent(ValidatableResponse response) {
       response.statusCode(SC_NOT_FOUND).assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    public void loginIsDuplicate(ValidatableResponse response) {
        response.statusCode(SC_CONFLICT).assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}
