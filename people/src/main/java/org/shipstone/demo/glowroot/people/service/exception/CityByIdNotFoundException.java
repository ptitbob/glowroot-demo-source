package org.shipstone.demo.glowroot.people.service.exception;

public class CityByIdNotFoundException extends Exception {

  public CityByIdNotFoundException(String zipcode) {
    super(String.format("Il n'existe pas commune avec l'identifiant %s", zipcode));
  }

}
