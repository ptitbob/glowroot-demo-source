package org.shipstone.demo.glowroot.people.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shipstone.demo.glowroot.commons.dto.CityDto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data @AllArgsConstructor @NoArgsConstructor
@JsonInclude(NON_NULL)
public class PersonDTO {

  private Long id;

  private String login;

  private String firstname;

  private String lastname;

  private CityDto city;

  /**
   * necessaire pour la création - bad pattern i know that ! but it's fine, it's for demo
   */
  private String zipcode;

  /**
   * necessaire pour la création - bad pattern i know that ! but it's fine, it's for demo
   */
  private String cityInseeId;

}
