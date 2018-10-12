package org.shipstone.demo.glowroot.people.web.dto;

import org.mapstruct.Mapper;
import org.shipstone.demo.glowroot.people.domain.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

  PersonDTO personDto(Person person);

  Person person(PersonDTO personDTO);

}
