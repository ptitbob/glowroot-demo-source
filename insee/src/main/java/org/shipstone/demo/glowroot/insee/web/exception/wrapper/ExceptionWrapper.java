package org.shipstone.demo.glowroot.insee.web.exception.wrapper;

import org.shipstone.demo.glowroot.insee.web.exception.CityNotFoundException;
import org.shipstone.demo.glowroot.insee.web.exception.MultiCityForOneZipcodeException;
import org.shipstone.demo.glowroot.insee.web.exception.NoCityForOneZipcodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@ControllerAdvice
public class ExceptionWrapper {

  @ExceptionHandler({MultiCityForOneZipcodeException.class, NoCityForOneZipcodeException.class})
  public ResponseEntity<String> multiCityForOneZipcodeExceptionHandler(
    HttpServletRequest httpServletRequest,
    MultiCityForOneZipcodeException e
  ) {
    return new ResponseEntity<>(e.getMessage(), PRECONDITION_FAILED);
  }

  @ExceptionHandler({CityNotFoundException.class})
  public ResponseEntity<String> cityNotFoundExceptionHandler (
      HttpServletRequest httpServletRequest,
      CityNotFoundException e
  ) {
    return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
  }

}
