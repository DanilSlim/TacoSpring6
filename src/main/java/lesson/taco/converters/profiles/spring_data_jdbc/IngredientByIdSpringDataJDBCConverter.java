package lesson.taco.converters.profiles.spring_data_jdbc;

import lesson.taco.domain.spring_data_jdbc.Ingredient;
import lesson.taco.data.spring_data_jdbc.IngredientRepositorySpringDataJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Profile("spring_data_jdbc")
@Component
public class IngredientByIdSpringDataJDBCConverter implements Converter <String, Ingredient>{

   private final IngredientRepositorySpringDataJDBC ingredientRepo;

   @Autowired
   public IngredientByIdSpringDataJDBCConverter(IngredientRepositorySpringDataJDBC ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }


    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findByName(id).orElse(null);
    }
}
