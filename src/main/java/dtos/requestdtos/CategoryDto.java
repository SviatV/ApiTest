
package dtos.requestdtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class CategoryDto {

  private int id;
  private String name;
}
