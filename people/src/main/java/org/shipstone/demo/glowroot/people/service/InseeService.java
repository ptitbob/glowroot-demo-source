package org.shipstone.demo.glowroot.people.service;

import org.shipstone.demo.glowroot.commons.dto.CityDto;
import org.shipstone.demo.glowroot.people.service.exception.CityByIdNotFoundException;
import org.shipstone.demo.glowroot.people.service.exception.CityByZipCodeNotFoundException;
import org.shipstone.demo.glowroot.people.service.exception.TooManyCityForZipcodeException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Service
public class InseeService {

  public CityDto getCityByInseeId(String cityInseeId) throws CityByIdNotFoundException {
    try {
      return new RestTemplate().exchange(
          fromUriString("http://localhost").port(8081).pathSegment("cities",cityInseeId).build(true).toUri(),
          GET,
          new HttpEntity<>(new HttpHeaders()),
          CityDto.class
      ).getBody();
    } catch (HttpClientErrorException e) {
      if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
        throw new CityByIdNotFoundException(cityInseeId);
      }
      return null;
    }
  }

  public CityDto getCityByZipCode(String zipcode) throws CityByZipCodeNotFoundException, TooManyCityForZipcodeException {
    try {
      ResponseEntity<List<CityDto>> listResponseEntity = new RestTemplate().exchange(
          fromUriString("http://localhost").port(8081).pathSegment("cities").queryParam("zipcode", zipcode).build(true).toUri(),
          GET,
          new HttpEntity<>(new HttpHeaders()),
          new ParameterizedTypeReference<List<CityDto>>(){} // Warning, it's javac unsafe operation if use ParameterizedTypeReference<> on IntelliJ advice
      );
      if (listResponseEntity.hasBody()) {
        List<CityDto> cityDtoList = listResponseEntity.getBody();
        if (cityDtoList != null) {
          if (cityDtoList.size() > 1) {
            throw new TooManyCityForZipcodeException(zipcode);
          }
          return cityDtoList.get(0);
        }
      }
      throw new CityByZipCodeNotFoundException(zipcode);
    } catch (HttpServerErrorException e) {
      throw new CityByZipCodeNotFoundException(zipcode);
    }
  }
}
