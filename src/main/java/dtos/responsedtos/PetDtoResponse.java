
package dtos.responsedtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PetDtoResponse {

  private CategoryDtoResponse category;
  private int id;
  private String name;
  private ArrayList<String> photoUrls;
  private String status;
  private List<TagDtoResponse> tags;
}
