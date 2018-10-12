package org.shipstone.demo.glowroot.people.web.exception;

import org.shipstone.demo.glowroot.people.service.exception.CityByIdNotFoundException;
import org.shipstone.demo.glowroot.people.service.exception.CityByZipCodeNotFoundException;
import org.shipstone.demo.glowroot.people.service.exception.PersonNotFouncException;
import org.shipstone.demo.glowroot.people.service.exception.TooManyCityForZipcodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionWrapper {

  @ExceptionHandler(PersonNotFouncException.class)
  public ResponseEntity<String> personNotFouncExceptionHandler(
      HttpServletRequest httpServletRequest,
      PersonNotFouncException e
  ) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InconsistentPathIdException.class)
  public ResponseEntity<String> inconsistentPathIdException(
      HttpServletRequest httpServletRequest,
      InconsistentPathIdException e
  ) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InconsistentPersonException.class)
  public ResponseEntity<String> inconsistentPersonExceptionHandler(
      HttpServletRequest httpServletRequest,
      InconsistentPersonException e
  ) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CityByIdNotFoundException.class)
  public ResponseEntity<String> cityByIdNotFoundExceptionHandler(
      HttpServletRequest httpServletRequest,
      CityByIdNotFoundException e
  ) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CityByZipCodeNotFoundException.class)
  public ResponseEntity<String> cityByZipCodeNotFoundExceptionHandler(
      HttpServletRequest httpServletRequest,
      CityByZipCodeNotFoundException e
  ) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TooManyCityForZipcodeException.class)
  public ResponseEntity<String> tooManyCityForZipcodeExceptionandler(
      HttpServletRequest httpServletRequest,
      TooManyCityForZipcodeException e
  ) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
