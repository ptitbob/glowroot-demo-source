package org.shipstone.demo.glowroot.insee.web.exception;

public class CityNotFoundException extends Exception {
  public CityNotFoundException(String inseeId) {
    super(String.format("Il n'existe pas de commune avec le code insee %s", inseeId));
  }
}
