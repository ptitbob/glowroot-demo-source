package org.shipstone.demo.glowroot.people.service.exception;

public class PersonNotFouncException extends Exception {

  public PersonNotFouncException(Long personId) {
    super(String.format("La personne (%d) n'a pas pu être localisé", personId));
  }

}
