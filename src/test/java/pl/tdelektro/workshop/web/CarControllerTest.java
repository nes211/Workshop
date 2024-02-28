package pl.tdelektro.workshop.web;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import pl.tdelektro.workshop.pojo.Car;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class CarControllerTest {

    private String password = "";
    private Properties properties;

    @Before
    public void setup() throws IOException {

        // Resources retrieve password from configuration file
        ClassPathResource resource = new ClassPathResource("admin.properties");
        properties = PropertiesLoaderUtils.loadProperties(resource);
        password = properties.getProperty("admin.password", "password");

        // Rest Assured - set up authentication
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("workshop@tdelektro.pl");
        authScheme.setPassword(password);

        // Rest Assured - apply authentication filter
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8080)
                .setBasePath("/car")
                .setAuth(authScheme)
                .build();

        RestAssured.requestSpecification = requestSpec;
    }

    @Test
    public void getCar() {
        given()
                //.auth().preemptive().basic("workshop@tdelektro.pl", password)
                .when()
                .get("/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("model", equalTo("Pagani Zonda"));
    }

    @Test
    public void getAllCars() {

        List<Car> cars = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/all")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .extract()
                    .body()
                    .jsonPath()
                    .getList(".", Car.class);


        assertThat(cars, hasSize(greaterThan(0)));
    }

    @Test
    public void deleteCar() {

        int carId = 1;
        RestAssured
                .given()
                .delete("/delete/{carId}", carId)
                .then()
                .statusCode(204);
    }

    @Test
    public void addCar() {

        String testBody = "{\"vinNumber\":\"1HGCM82633A001234\",\"model\":\"Polonez 1500\"}";
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(testBody)
                .post("/add")
                .then()
                .statusCode(201);
    }


}
