package com.afos.app.repository;

import com.afos.app.bean.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*VJ: Spring Data repositories (CrudRepository,PagingAndSortingRepository,JpaRepository)
https://www.baeldung.com/spring-data-repositories#overview
* */
@Repository
public interface NutrientRepository extends JpaRepository<Nutrient,Integer> {
    Optional<Nutrient> findByName(String name);
}
