package lesson.taco.domain.spring_data_jdbc;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lesson.taco.data.jdbc_template.IngredientRef;
import lesson.taco.domain.Ingredient;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;

@Profile("spring_data_jdbc")
@Data
@Table("TACO")
public class TacoSpringDataJDBC {

    @Id
    private Long id;

    private Date createdAt =new Date();

    @NotNull
    @Size(min = 5,message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients;

    public void addIngredient(Ingredient taco) {
        this.ingredients.add(new IngredientRef(taco.id()));
    }
}
