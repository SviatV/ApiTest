
package dtos.requestdtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data

public class PetDto {

  private CategoryDto category;
  private int id;
  private String name;
  private ArrayList<String> photoUrls;
  private String status;
  private ArrayList<TagDto> tags;
}
