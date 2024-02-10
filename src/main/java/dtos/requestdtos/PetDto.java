
package dtos.requestdtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class PetDto {

  private CategoryDto category;
  private int id;
  private String name;
  private ArrayList<String> photoUrls;
  private String status;
  private ArrayList<TagDto> tagDtos;
}
