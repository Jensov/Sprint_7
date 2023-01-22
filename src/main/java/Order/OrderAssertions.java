package Order;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {
    public void createdSuccessfully(ValidatableResponse response) {
        response.statusCode(201).assertThat().body("track", notNullValue());
    }
}
