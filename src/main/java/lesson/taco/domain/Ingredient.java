package lesson.taco.domain;

import org.springframework.context.annotation.Profile;

@Profile("simple,jdbc_template")
public record Ingredient(String id, String name, lesson.taco.domain.Ingredient.Type type) {

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    public Type getType(){
        return this.type;
    }
}
