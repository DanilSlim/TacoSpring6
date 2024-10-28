package lesson.taco.data.spring_data_jpa;

import jakarta.transaction.Transactional;
import lesson.taco.domain.spring_data_jpa.IngredientRef;
import lesson.taco.domain.spring_data_jpa.IngredientRefKey;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Profile("spring_data_jpa")
@Repository
public interface IngredientRefRepositorySpringDataJPA extends JpaRepository<IngredientRef, IngredientRefKey> {

    @Transactional
    @Modifying
    @Query("update IngredientRef ingredientRef set ingredientRef.tacoKey= :tacoKey where ingredientRef.ingredient= :ingredient and ingredientRef.tacoId= :tacoId ")
    int updateIngredientRef(@Param("ingredient") String ingredient,@Param("tacoId") long tacoId,
                                      @Param("tacoKey") long tacoKey);

    IngredientRef findByIngredientAndTacoId(String ingredient, long tacoId);
}
