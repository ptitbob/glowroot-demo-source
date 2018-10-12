package org.shipstone.demo.glowroot.people.service;

import org.shipstone.demo.glowroot.commons.dto.CityDto;
import org.shipstone.demo.glowroot.people.domain.Person;
import org.shipstone.demo.glowroot.people.repository.PersonRepository;
import org.shipstone.demo.glowroot.people.service.exception.PersonNotFouncException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Service
@Transactional(readOnly = true)
public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Page<Person> getAll(
      Pageable pageable
  ) {
    return this.personRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Person getPersonById(final Long personId) throws PersonNotFouncException {
    try {
      Thread.sleep(150);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return personRepository.findById(personId).orElseThrow(() -> new PersonNotFouncException(personId));
  }

  @Transactional
  public Person createPerson(Person person) {
    person.setId(null);
    return personRepository.save(person);
  }

  @Transactional
  public Person update(Person person) throws PersonNotFouncException {
    Person personToUpdate = getPersonById(person.getId());
    personToUpdate.setLogin(person.getLogin());
    personToUpdate.setFirstname(person.getFirstname());
    personToUpdate.setLastname(person.getLastname());
    personRepository.save(personToUpdate);
    return personToUpdate;
  }

  @Transactional
  public void deletePerson(Long personId) throws PersonNotFouncException {
    Person personToDelete = personRepository.findById(personId).orElseThrow(() -> new PersonNotFouncException(personId));
    personRepository.delete(personToDelete);
  }

  @Transactional
  public void deletePeople() {
    personRepository.deletePeople();
  }

  public boolean checkZipcode(String zipcode) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      new RestTemplate().exchange(
          fromUriString("http://localhost").port(8081).queryParam("zipcode", zipcode).build(true).toUri(),
          GET,
          new HttpEntity(new HttpHeaders()),
          String.class
      );
    } catch (HttpStatusCodeException e) {
      return false;
    }
    return true;
  }

  public CityDto findCityInformationFor(String zipcode) {
    ResponseEntity<CityDto> responseEntity = new RestTemplate().exchange(
        fromUriString("http://localhost").port(8081).path(zipcode).build(true).toUri(),
        GET,
        new HttpEntity(new HttpHeaders()),
        CityDto.class
    );
    if (responseEntity.hasBody()) {
      return responseEntity.getBody();
    }
    return null;
  }

}
