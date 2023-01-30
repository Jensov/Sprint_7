package Order;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.notNullValue;
import static org.apache.http.HttpStatus.*;

public class OrderAssertions {
    public void createdSuccessfully(ValidatableResponse response) {
        response.statusCode(SC_CREATED).assertThat().body("track", notNullValue());
    }
}
