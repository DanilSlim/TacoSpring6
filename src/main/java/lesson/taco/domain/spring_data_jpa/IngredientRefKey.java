package lesson.taco.domain.spring_data_jpa;


import lombok.Data;

import java.io.Serializable;

@Data
public class IngredientRefKey implements Serializable {

    private String ingredient;
    private long tacoId;
}
