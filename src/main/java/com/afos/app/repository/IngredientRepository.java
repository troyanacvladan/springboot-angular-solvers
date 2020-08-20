package com.afos.app.repository;

import com.afos.app.bean.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*VJ: Spring Data repositories (CrudRepository,PagingAndSortingRepository,JpaRepository)
https://www.baeldung.com/spring-data-repositories#overview
* */
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {
}
