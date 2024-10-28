package lesson.taco.domain.spring_data_jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Profile("spring_data_jdbc")
@Table
public record Ingredient(@Id String id, String name, Type type) {

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    public Type getType(){
        return this.type;
    }
}
