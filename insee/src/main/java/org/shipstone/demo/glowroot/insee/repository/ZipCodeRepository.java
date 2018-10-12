package org.shipstone.demo.glowroot.insee.repository;

import org.shipstone.demo.glowroot.insee.domain.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode, Long> {

  List<ZipCode> findByZipcode(String zipcode);

  @Query("select count(z) >= 1 from ZipCode z where z.zipcode = :zipcode")
  boolean zipCodeExist(@Param("zipcode") String zipCode);

  List<ZipCode> findByCityInseeId(String cityInseeId);

}
