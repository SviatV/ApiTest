package services;

import static io.restassured.RestAssured.given;

import dtos.requestdtos.PetDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PetService {
  private static final String BASE_URI = "https://petstore.swagger.io/v2/";
  private static final String BASE_PATH = "/pet";

  private RequestSpecification requestSpecification;

  public void serviceApi() {
    requestSpecification = given()
            .baseUri(BASE_URI)
            .contentType(ContentType.JSON)
            .log().all();
  }

  public ValidatableResponse createPet(PetDto petDto) {
    serviceApi();
    return given(requestSpecification)
            .basePath(BASE_PATH)
            .body(petDto)
            .when()
            .post()
            .then()
            .log().all();
  }

  public ValidatableResponse getPet(int id) {
    serviceApi();
    return given(requestSpecification)
            .basePath(BASE_PATH + "/" + id)
            .when()
            .get()
            .then()
            .log()
            .all();
  }

  public ValidatableResponse deletePet(int id, String apiKey) {
    serviceApi();
    return given(requestSpecification)
            .header("api_key", apiKey)
            .basePath(BASE_PATH + "/" + id)
            .when()
            .delete()
            .then()
            .log()
            .all();
  }

  public ValidatableResponse putPet(PetDto petDto) {
    serviceApi();
    return given(requestSpecification)
            .basePath(BASE_PATH)
            .body(petDto)
            .when()
            .post()
            .then()
            .log().all();
  }
}
