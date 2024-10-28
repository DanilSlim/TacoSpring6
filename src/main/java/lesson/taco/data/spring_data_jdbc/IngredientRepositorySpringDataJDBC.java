package lesson.taco.data.spring_data_jdbc;

import lesson.taco.domain.spring_data_jdbc.Ingredient;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;
import java.util.Optional;

@Profile("spring_data_jdbc")
public interface IngredientRepositorySpringDataJDBC extends Repository<Ingredient, String>
{
    Optional<Ingredient> findByName(String id);
    Iterable<Ingredient> findAll();
    Ingredient save(Ingredient ingredient);
}
