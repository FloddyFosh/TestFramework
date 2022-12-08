package helpers;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiHelper {

    public static void askJochem() {

        given().log().all().and()
            .spec(getRequestSpecification())
            .when()
            .post("/askJochem")
            .then().log().all().and()
            .assertThat()
            .statusCode(200);
    }

    public static void askJochemWeather(String day) {
        given().log().all().and()
                .spec(getRequestSpecification())
                .param("search_term", day)
                .when()
                .get("/askJochem/weather")
                .then().log().all().and()
                .assertThat()
                .statusCode(200);
    }

    private static RequestSpecification getRequestSpecification() {

        return new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            //.setPort(5000)
            .build();
    }
}