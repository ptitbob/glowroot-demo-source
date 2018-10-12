package org.shipstone.demo.glowroot.people.web.exception;

public class InconsistentPersonException extends Exception {

  public InconsistentPersonException() {
    super("Les Informations de la personne ne sont pas exploitable");
  }
}
