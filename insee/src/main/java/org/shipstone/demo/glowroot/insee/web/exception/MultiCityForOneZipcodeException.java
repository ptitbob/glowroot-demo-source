package org.shipstone.demo.glowroot.insee.web.exception;

public class MultiCityForOneZipcodeException extends Exception {

  public MultiCityForOneZipcodeException(String zipcode) {
    super(String.format("Le code postal %s designe plusieurs communes", zipcode));
  }
}
