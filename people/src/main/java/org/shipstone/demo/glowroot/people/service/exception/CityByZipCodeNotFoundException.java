package org.shipstone.demo.glowroot.people.service.exception;

public class CityByZipCodeNotFoundException extends Exception {
  public CityByZipCodeNotFoundException(String zipcode) {
    super(String.format("Il n'y a pas de commune avec le code postal %s", zipcode));
  }
}
