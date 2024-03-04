package services;

import dtos.requestdtos.PetDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PetService {

  private RequestSpecification requestSpecification = RestAssured.given()
          .baseUri(System.getProperty("petstore.baseURI"))
          .basePath(System.getProperty("pet.basePath"))
          .contentType(ContentType.JSON)
          .log().all();

  public ValidatableResponse createPet(PetDto petDto) {
    return RestAssured.given(requestSpecification)
            .body(petDto)
            .when()
            .post()
            .then()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/CreatePetSchema.json"))
            .log().all();
  }

  public ValidatableResponse getPet(int id) {
    return RestAssured.given(requestSpecification)
            .when()
            .get(String.valueOf(id))
            .then()
            .log()
            .all();
  }

  public ValidatableResponse deletePet(int id, String apiKey) {
    return RestAssured.given(requestSpecification)
            .header("api_key", apiKey)
            .when()
            .delete(String.valueOf(id))
            .then()
            .log()
            .all();
  }

  public ValidatableResponse putPet(PetDto petDto) {
    return RestAssured.given(requestSpecification)
            .body(petDto)
            .when()
            .post()
            .then()
            .log().all();
  }
}
