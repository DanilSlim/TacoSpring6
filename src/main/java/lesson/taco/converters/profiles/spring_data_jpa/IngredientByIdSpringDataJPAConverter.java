package lesson.taco.converters.profiles.spring_data_jpa;


import lesson.taco.data.spring_data_jpa.IngredientRepositorySpringDataJPA;
import lesson.taco.domain.spring_data_jpa.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Profile("spring_data_jpa")
@Component
public class IngredientByIdSpringDataJPAConverter implements Converter <String, Ingredient>{

   private final IngredientRepositorySpringDataJPA ingredientRepo;

   @Autowired
   public IngredientByIdSpringDataJPAConverter(IngredientRepositorySpringDataJPA ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }


    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
