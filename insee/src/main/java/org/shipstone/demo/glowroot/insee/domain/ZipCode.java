package org.shipstone.demo.glowroot.insee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "zipcode")
@Data @NoArgsConstructor @AllArgsConstructor
public class ZipCode {

  @Id @Column(name = "zipcode_id")
  private Long id;

  @Column(name = "zipcode")
  private String zipcode;

  @Column(name = "city_inseeid")
  private String cityInseeId;

}
