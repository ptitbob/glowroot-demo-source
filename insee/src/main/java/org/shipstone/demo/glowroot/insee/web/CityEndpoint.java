package org.shipstone.demo.glowroot.insee.web;

import org.shipstone.demo.glowroot.commons.dto.CityDto;
import org.shipstone.demo.glowroot.commons.web.ResponseEntityProcessor;
import org.shipstone.demo.glowroot.insee.domain.City;
import org.shipstone.demo.glowroot.insee.domain.ZipCode;
import org.shipstone.demo.glowroot.insee.service.CityService;
import org.shipstone.demo.glowroot.insee.web.dto.CityMapper;
import org.shipstone.demo.glowroot.insee.web.exception.CityNotFoundException;
import org.shipstone.demo.glowroot.insee.web.exception.NoCityForOneZipcodeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "cities", produces = {APPLICATION_JSON_VALUE})
public class CityEndpoint implements ResponseEntityProcessor {

  private final CityService cityService;

  private final CityMapper cityMapper;

  public CityEndpoint(CityService cityService, CityMapper cityMapper) {
    this.cityService = cityService;
    this.cityMapper = cityMapper;
  }

  @GetMapping
  public ResponseEntity<List<CityDto>> getAll(
      @RequestParam(value = "zipcode", required = false) String zipcode,
      @PageableDefault Pageable pageable
  ) throws NoCityForOneZipcodeException {
    return responseEntity(cityService.getAll(zipcode, pageable), cityMapper::cityDto);
  }

  @GetMapping("{inseeId:\\d{5}}")
  public CityDto getCityByInseeId(
      @PathVariable("inseeId") final String inseeId
  ) throws CityNotFoundException {
    CityDto cityDto = cityMapper.cityDto(this.cityService.getByInseeId(inseeId)
            .orElseThrow(() -> new CityNotFoundException(inseeId)));
    cityDto.setZipCode(this.cityService.getZipCodeList(cityDto.getInseeId()).stream().map(ZipCode::getZipcode).collect(Collectors.toList()));
    return cityDto;
  }

}
