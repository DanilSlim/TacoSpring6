package lesson.taco.data.jdbc_template;

import org.springframework.context.annotation.Profile;

@Profile("jdbc_template")
    public record IngredientRef(String ingredient) {

}
