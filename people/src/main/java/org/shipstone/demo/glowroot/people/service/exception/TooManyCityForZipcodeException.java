package org.shipstone.demo.glowroot.people.service.exception;

public class TooManyCityForZipcodeException extends Exception {
  public TooManyCityForZipcodeException(String zipcode) {
    super(String.format("Il y a plus d'une commune attachée au code postal %s, vous devez en désigner une", zipcode));
  }
}
