package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dtobuilders.PetDtoBuilder;
import dtos.requestdtos.PetDto;
import dtos.responsedtos.PetDtoResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PetService;

import java.util.Random;

public class PetTests {
  PetService petService = new PetService();
  private PetDto petDto;
  private ValidatableResponse getPetResponse;
  private ValidatableResponse createPetResponse;

  @DisplayName("Create a new pat and verify that a new pet was created")
  @Test
  public void createPet() {
    petDto = PetDtoBuilder.buildPetDto();
    createPetResponse = petService.createPet(petDto);
    createPetResponse.statusCode(HttpStatus.SC_OK);
    createPetResponse.time(Matchers.lessThan(5000L));
    createPetResponse.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/CreatePetSchema.json"));
    getPetResponse = petService.getPet(petDto.getId());
    getPetResponse.statusCode(HttpStatus.SC_OK);
    PetDtoResponse petDtoResponseBody = getPetResponse.extract().body().as(PetDtoResponse.class);
    Assertions.assertAll(
        () -> assertEquals(petDto.getId(), petDtoResponseBody.getId(), "IDs are not equal"),
        () -> assertEquals(petDto.getName(), petDtoResponseBody.getName(), "Names of pets are not equal")
    );
  }

  @DisplayName("Verify that the getPet returns a correct status code if the a pet is not found")
  @Test
  public void getPet() {
    getPetResponse = petService.getPet(new Random().nextInt(100));
    getPetResponse.statusCode(HttpStatus.SC_NOT_FOUND);
  }


  @DisplayName("verify that the pet can be deleted")
  @Test
  public void deletePet() {
    petDto = PetDtoBuilder.buildPetDto();
    createPetResponse = petService.createPet(petDto);
    createPetResponse.statusCode(HttpStatus.SC_OK);
    ValidatableResponse deletePetResponse = petService.deletePet(petDto.getId(), "delete");
    deletePetResponse.statusCode(HttpStatus.SC_OK);
    getPetResponse = petService.getPet(petDto.getId());
    getPetResponse.statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @DisplayName("Verify that pet name is updated")
  @Test
  public void updatePet() {
    petDto = PetDtoBuilder.buildPetDto();
    PetDto updatedName = PetDtoBuilder.buildUpdatedPetDto();
    createPetResponse = petService.createPet(petDto);
    createPetResponse.statusCode(HttpStatus.SC_OK);
    ValidatableResponse putPetResponse = petService.putPet(updatedName);
    PetDtoResponse petDtoResponse = putPetResponse.extract().body().as(PetDtoResponse.class);
    putPetResponse.statusCode(HttpStatus.SC_OK);
    Assertions.assertEquals(updatedName.getName(), petDtoResponse.getName(), "Pet name has not been update");
  }
}
