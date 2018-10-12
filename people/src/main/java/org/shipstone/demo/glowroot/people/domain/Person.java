package org.shipstone.demo.glowroot.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@SequenceGenerator(sequenceName = "seq_person", name = "seq_person", initialValue = 1, allocationSize = 1 )
@Data @NoArgsConstructor @AllArgsConstructor
public class Person {

  @Id @Column(name = "person_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person")
  private Long id;

  @Column(name = "login")
  private String login;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "city_inseeid")
  private String cityInseeId;

}
