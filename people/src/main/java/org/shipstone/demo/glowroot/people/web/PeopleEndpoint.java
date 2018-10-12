package org.shipstone.demo.glowroot.people.web;

import org.shipstone.demo.glowroot.commons.dto.CityDto;
import org.shipstone.demo.glowroot.commons.web.ResponseEntityProcessor;
import org.shipstone.demo.glowroot.people.domain.Person;
import org.shipstone.demo.glowroot.people.service.InseeService;
import org.shipstone.demo.glowroot.people.service.PersonService;
import org.shipstone.demo.glowroot.people.service.exception.CityByZipCodeNotFoundException;
import org.shipstone.demo.glowroot.people.service.exception.PersonNotFouncException;
import org.shipstone.demo.glowroot.people.service.exception.TooManyCityForZipcodeException;
import org.shipstone.demo.glowroot.people.web.dto.PersonDTO;
import org.shipstone.demo.glowroot.people.web.dto.PersonMapper;
import org.shipstone.demo.glowroot.people.service.exception.CityByIdNotFoundException;
import org.shipstone.demo.glowroot.people.web.exception.InconsistentPathIdException;
import org.shipstone.demo.glowroot.people.web.exception.InconsistentPersonException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("people")
public class PeopleEndpoint implements ResponseEntityProcessor {

  private final PersonService personService;

  private final InseeService inseeService;

  private final PersonMapper personMapper;

  public PeopleEndpoint(PersonService personService, InseeService inseeService, PersonMapper personMapper) {
    this.personService = personService;
    this.inseeService = inseeService;
    this.personMapper = personMapper;
  }

  @GetMapping
  public ResponseEntity<List<PersonDTO>> getAll(
      @PageableDefault Pageable pageable
  ){
    return responseEntity(personService.getAll(pageable), personMapper::personDto);
  }

  @GetMapping("{personId:\\d*}")
  public PersonDTO getById(
      @PathVariable("personId") Long personId
  ) throws PersonNotFouncException, CityByIdNotFoundException {
    PersonDTO personDTO = personMapper.personDto(personService.getPersonById(personId));
    if (isNotBlank(personDTO.getCityInseeId())) {
      personDTO.setCity(inseeService.getCityByInseeId(personDTO.getCityInseeId()));
      personDTO.setCityInseeId(null);
    }
    return personDTO;
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE})
  public ResponseEntity<PersonDTO> createPerson(
      UriComponentsBuilder uriComponentsBuilder,
      @RequestHeader HttpHeaders requestHttpHeaders,
      @RequestBody PersonDTO personDTO
  ) throws InconsistentPersonException, CityByIdNotFoundException, CityByZipCodeNotFoundException, TooManyCityForZipcodeException {
    if (isBlank(personDTO.getLogin())) {
      throw new InconsistentPersonException();
    }
    fixCity(personDTO);
    Person createdPerson = personService.createPerson(personMapper.person(personDTO));
    return responseEntity(
        personMapper.personDto(createdPerson),
        requestHttpHeaders,
        uriComponentsBuilder.path("/{id}").buildAndExpand(createdPerson.getId()).toUri()
    );
  }

  @PutMapping(path = "{personId:\\d*}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE})
  public ResponseEntity<PersonDTO> updatePerson(
      @PathVariable(name = "personId") Long personId,
      @RequestHeader HttpHeaders requestHttpHeaders,
      @RequestBody PersonDTO personDTO
  ) throws InconsistentPathIdException, PersonNotFouncException, CityByIdNotFoundException, CityByZipCodeNotFoundException, TooManyCityForZipcodeException {
    if (personDTO.getId() == null || !personDTO.getId().equals(personId)) {
      throw new InconsistentPathIdException();
    }
    fixCity(personDTO);
    Person person = personService.update(personMapper.person(personDTO));
    return responseEntity(personMapper.personDto(person), requestHttpHeaders);
  }

  private void fixCity(@RequestBody PersonDTO personDTO) throws CityByIdNotFoundException, CityByZipCodeNotFoundException, TooManyCityForZipcodeException {
    if (isNotBlank(personDTO.getCityInseeId())) {
      this.inseeService.getCityByInseeId(personDTO.getCityInseeId());
    } else if (isNotBlank(personDTO.getZipcode())) {
      CityDto cityDto = this.inseeService.getCityByZipCode(personDTO.getZipcode());
      personDTO.setCityInseeId(cityDto.getInseeId());
    }
  }

  @DeleteMapping("{personId:\\d*}")
  public ResponseEntity<PersonDTO> deletePerson(
      @PathVariable(name = "personId") Long personId
  ) {
    try {
      personService.deletePerson(personId);
    } catch (PersonNotFouncException e) {
      // do nothing if not found
    }
    return new ResponseEntity<>(OK);
  }

  @DeleteMapping
  public ResponseEntity<String> deletePeople() {
    personService.deletePeople();
    return new ResponseEntity<>(OK);
  }

}
