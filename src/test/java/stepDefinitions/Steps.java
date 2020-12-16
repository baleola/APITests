package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class Steps {
    private static final String BASE_URI = "http://api.citybik.es/v2/";


    @Given("I send a GET request to the networks endpoint")
    public void i_send_a_get_request_to_the_networks_endpoint() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.given().
                get("/networks");

    }


    @Then("I receive a valid response")
    public void i_receive_a_valid_response() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.given().
                get("/networks")
                .then().statusCode(Matchers.is(200));

    }


    @Given("I confirm the country is Germany")
    public void i_confirm_the_country_is_germany() {
        RestAssured.baseURI = BASE_URI;

        Response res = RestAssured.given().when().
                get("/networks/visa-frankfurt");

        res.then().statusCode(200)
                .body("network.location.city", Matchers.equalTo("Frankfurt"))
                .body("network.location.country", Matchers.equalTo("DE"));

    }

    @Then("I can return the latitude and longitude")
    public void i_can_return_the_latitude_and_longitude() {
        RestAssured.baseURI = BASE_URI;

        Response res = RestAssured.given().when().
                get("/networks/visa-frankfurt");

        res.then().statusCode(200)
                .body("network.location.city", Matchers.equalTo("Frankfurt"))
                .body("network.location.country", Matchers.equalTo("DE"));

        float latitude = res.then().extract().path("network.location.latitude");
        float longitude = res.then().extract().path("network.location.longitude");
        System.out.println("Frankfurt Latitude and Longitude");
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
    }


}
