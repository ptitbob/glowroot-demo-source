package org.shipstone.demo.glowroot.insee.repository;

import org.shipstone.demo.glowroot.insee.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

  Optional<City> findByInseeId(String cityInseeId);

  Page<City> findByInseeIdIn(List<String> InseeIdList, Pageable pageable);

}
