package org.shipstone.demo.glowroot.insee.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@JsonInclude
public class City {

  @Id @Column(name = "city_inseeid")
  private String inseeId;

  @Column(name = "name")
  private String name;

}
