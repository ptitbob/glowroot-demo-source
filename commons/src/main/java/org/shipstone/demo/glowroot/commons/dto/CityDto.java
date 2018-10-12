package org.shipstone.demo.glowroot.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonInclude(NON_NULL)
public class CityDto {

  private String inseeId;

  private String name;

  private List<String> zipCode;

}
