package org.shipstone.demo.glowroot.insee.service;

import org.apache.commons.lang3.StringUtils;
import org.shipstone.demo.glowroot.insee.domain.City;
import org.shipstone.demo.glowroot.insee.domain.ZipCode;
import org.shipstone.demo.glowroot.insee.repository.CityRepository;
import org.shipstone.demo.glowroot.insee.repository.ZipCodeRepository;
import org.shipstone.demo.glowroot.insee.web.exception.CityNotFoundException;
import org.shipstone.demo.glowroot.insee.web.exception.NoCityForOneZipcodeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

  private final CityRepository cityRepository;

  private final ZipCodeRepository zipCodeRepository;

  public CityService(CityRepository cityRepository, ZipCodeRepository zipCodeRepository) {
    this.cityRepository = cityRepository;
    this.zipCodeRepository = zipCodeRepository;
  }

  public Page<City> getAll(String zipcode, Pageable pageable) throws NoCityForOneZipcodeException {
    if (StringUtils.isNotBlank(zipcode)) {
      List<ZipCode> zipCodeList = this.zipCodeRepository.findByZipcode(zipcode);
      if (zipCodeList.isEmpty()) {
        throw new NoCityForOneZipcodeException(zipcode);
      }
      return this.cityRepository.findByInseeIdIn(zipCodeList.stream().map(ZipCode::getCityInseeId).collect(Collectors.toList()), pageable);
    }
    return this.cityRepository.findAll(pageable);
  }

  public Optional<City> getByInseeId(String inseeId) {
    return this.cityRepository.findByInseeId(inseeId);
  }

  public List<ZipCode> getZipCodeList(String inseeId) {
    return this.zipCodeRepository.findByCityInseeId(inseeId);
  }
}
