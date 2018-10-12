package org.shipstone.demo.glowroot.insee.web.dto;

import org.mapstruct.Mapper;
import org.shipstone.demo.glowroot.insee.domain.ZipCode;

@Mapper(componentModel = "spring")
public interface ZipCodeMapper {

  String zipCodeDto(ZipCode zipCode);

  ZipCode zipCode(String zipCodeDto);

}
