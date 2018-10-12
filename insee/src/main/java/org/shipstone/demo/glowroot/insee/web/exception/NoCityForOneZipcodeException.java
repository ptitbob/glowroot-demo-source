package org.shipstone.demo.glowroot.insee.web.exception;

public class NoCityForOneZipcodeException extends Exception {
  public NoCityForOneZipcodeException(String zipcode) {
    super(String.format("Il n'existe pas de ville pour le code postal %s", zipcode));
  }
}
