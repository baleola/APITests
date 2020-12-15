package com.ola.APITest;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class GETTest {



    @Test
    void getSuccessfulStatusCode(){
        RestAssured.baseURI="http://api.citybik.es/v2/networks";

        RestAssured.given().
                get("/")
                .then().statusCode(Matchers.is(200));
    }



    @Test
    void getFrankfurtLatitudeAndLongitude(){

        RestAssured.baseURI="http://api.citybik.es/v2/networks";

        Response res = RestAssured.given().when().
                get("/visa-frankfurt");

        res.then().statusCode(200)
                .body("network.location.city",  Matchers.equalTo("Frankfurt"))
                .body("network.location.country", Matchers.equalTo("DE"));

        float latitude = res.then().extract().path("network.location.latitude");
        float longitude = res.then().extract().path("network.location.longitude");
        System.out.println("Frankfurt Latitude and Longitude");
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
    }
}
