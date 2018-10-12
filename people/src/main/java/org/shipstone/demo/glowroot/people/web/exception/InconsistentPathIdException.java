package org.shipstone.demo.glowroot.people.web.exception;

public class InconsistentPathIdException extends Exception {

  public InconsistentPathIdException() {
    super("Les données (id) sont incorrect entre l'URL et des données");
  }
}
