package lesson.taco.converters.profiles.simple;

import lesson.taco.annotations.RequiresSimpleConversion;
import lesson.taco.domain.Ingredient;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Profile("simple")
public class IngredientByIdSimpleConverter implements ConditionalGenericConverter {

    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdSimpleConverter() {
        ingredientMap.put("FLTO",
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        ingredientMap.put("COTO",
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        ingredientMap.put("GRBF",
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        ingredientMap.put("CARN",
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        ingredientMap.put("TMTO",
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        ingredientMap.put("LETC",
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
        ingredientMap.put("CHED",
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientMap.put("JACK",
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        ingredientMap.put("SLSA",
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        ingredientMap.put("SRCR",
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
    }



    /*@Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }*/

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        //return sourceType.getAnnotation(RequiresSimpleConversion.class)!=null;
        return true;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Ingredient.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        String id= (String) source;
        return ingredientMap.get(id);

    }
}
