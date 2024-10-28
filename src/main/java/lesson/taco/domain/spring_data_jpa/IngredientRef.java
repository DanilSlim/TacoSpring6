package lesson.taco.domain.spring_data_jpa;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "INGREDIENT_REF")
@IdClass(IngredientRefKey.class)
public class IngredientRef {

    @Id
    @Column(name = "INGREDIENT")
    private String ingredient;
    @Id
    @Column(name = "TACO")
    private long tacoId;

    @Column(name = "TACO_KEY")
    private Long tacoKey;

}
