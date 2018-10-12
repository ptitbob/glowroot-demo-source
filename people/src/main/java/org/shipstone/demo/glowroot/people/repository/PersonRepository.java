package org.shipstone.demo.glowroot.people.repository;

import org.shipstone.demo.glowroot.people.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  @Modifying
  @Query("delete from Person p")
  void deletePeople();

}
