package tests.negativetests;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PetService;

import java.util.Random;

public class PetNegativeTests {

  PetService petService = new PetService();
  private ValidatableResponse getPetResponse;

  @DisplayName("Verify that the getPet returns a correct status code if a pet is not found")
  @Test
  public void getPet() {
    getPetResponse = petService.getPet(new Random().nextInt(3, 1000));
    getPetResponse.statusCode(HttpStatus.SC_NOT_FOUND);
  }
}
