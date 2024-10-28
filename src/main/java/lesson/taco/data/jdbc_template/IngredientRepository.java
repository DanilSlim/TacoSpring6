package lesson.taco.data.jdbc_template;

import lesson.taco.domain.Ingredient;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Profile("jdbc_template")
public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
