package dtobuilders;

import dtos.requestdtos.CategoryDto;
import dtos.requestdtos.PetDto;
import dtos.requestdtos.TagDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class PetDtoBuilder {

  private static final Properties PROPERTIES;

  static {
    PROPERTIES = new Properties();
    try (InputStream inputStream = PetDtoBuilder.class.getClassLoader().getResourceAsStream("petdata.properties")) {
      if (inputStream != null) {
        PROPERTIES.load(inputStream);
      } else {
        throw new FileNotFoundException("petdata.properties not found in classpath");
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to load petdata.properties", e);
    }
  }

  public static PetDto buildPetDto() {
    ArrayList<String> photoUrls = new ArrayList<>();
    photoUrls.add(PROPERTIES.getProperty("photoUrls"));

    TagDto tagDto = TagDto.builder()
            .id(Integer.parseInt(PROPERTIES.getProperty("tagDto.id")))
            .name(PROPERTIES.getProperty("tagDto.name"))
            .build();
    ArrayList<TagDto> tagDtos = new ArrayList<>();
    tagDtos.add(tagDto);

    CategoryDto categoryDto = CategoryDto.builder()
            .id(Integer.parseInt(PROPERTIES.getProperty("category.id")))
            .name(PROPERTIES.getProperty("category.name"))
            .build();

    return PetDto.builder()
            .id(Integer.parseInt(PROPERTIES.getProperty("pet.id")))
            .category(categoryDto)
            .name(PROPERTIES.getProperty("pet.name"))
            .photoUrls(photoUrls)
            .tags(tagDtos)
            .status(PROPERTIES.getProperty("pet.status"))
            .build();
  }

  public static PetDto buildUpdatedPetDto() {
    ArrayList<String> photoUrls = new ArrayList<>();
    photoUrls.add(PROPERTIES.getProperty("photoUrls"));

    TagDto tagDto = TagDto.builder()
            .id(Integer.parseInt(PROPERTIES.getProperty("tagDto.id")))
            .name(PROPERTIES.getProperty("tagDto.name"))
            .build();
    ArrayList<TagDto> tagDtos = new ArrayList<>();
    tagDtos.add(tagDto);

    CategoryDto categoryDto = CategoryDto.builder()
            .id(Integer.parseInt(PROPERTIES.getProperty("category.id")))
            .name(PROPERTIES.getProperty("category.name"))
            .build();

    return PetDto.builder()
            .id(Integer.parseInt(PROPERTIES.getProperty("pet.id")))
            .category(categoryDto)
            .name(PROPERTIES.getProperty("pet.nameUpdated"))
            .photoUrls(photoUrls)
            .tags(tagDtos)
            .status(PROPERTIES.getProperty("pet.status"))
            .build();
  }
}
