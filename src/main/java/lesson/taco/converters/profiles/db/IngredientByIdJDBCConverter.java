package lesson.taco.converters.profiles.db;

import lesson.taco.data.jdbc_template.IngredientRepository;
import lesson.taco.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Profile("jdbc_template")
@Component
public class IngredientByIdJDBCConverter implements Converter <String, Ingredient>{

   private final IngredientRepository ingredientRepo;

   @Autowired
   public IngredientByIdJDBCConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }


    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
