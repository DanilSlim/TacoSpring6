package lesson.taco.data.spring_data_jpa;

import lesson.taco.domain.spring_data_jpa.Ingredient;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Profile("spring_data_jpa")
@Repository
public interface IngredientRepositorySpringDataJPA extends CrudRepository<Ingredient, String>
{

}
