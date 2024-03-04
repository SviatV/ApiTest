package tests.positivetests;

import dtobuilders.PetDtoBuilder;
import dtos.requestdtos.PetDto;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PetService;

public class PetPositiveTests {

  PetService petService = new PetService();
  private PetDto petDto;
  private ValidatableResponse getPetResponse;
  private ValidatableResponse createPetResponse;

  @DisplayName("Create a new pet and verify that a new pet was created")
  @Test
  public void createPet() {
    petDto = PetDtoBuilder.buildPetDto();
    createPetResponse = petService.createPet(petDto);
    createPetResponse.statusCode(HttpStatus.SC_OK);
    getPetResponse = petService.getPet(petDto.getId());
    getPetResponse.statusCode(HttpStatus.SC_OK);
    PetDto petDtoResponseBody = getPetResponse.extract().body().as(PetDto.class);
    Assertions.assertEquals(petDto, petDtoResponseBody, "Objects are not equal");
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
    PetDto petDtoResponse = putPetResponse.extract().body().as(PetDto.class);
    putPetResponse.statusCode(HttpStatus.SC_OK);
    Assertions.assertEquals(updatedName.getName(), petDtoResponse.getName(), "Pet name has not been update");
  }
}
