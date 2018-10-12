package org.shipstone.demo.glowroot.insee.web.dto;

import org.mapstruct.Mapper;
import org.shipstone.demo.glowroot.commons.dto.CityDto;
import org.shipstone.demo.glowroot.insee.domain.City;

@Mapper(componentModel = "spring")
public interface CityMapper {

  CityDto cityDto(City city);

  City city(CityDto cityDto);

}
