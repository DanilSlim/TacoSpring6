package lesson.taco.domain.simple;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lesson.taco.domain.Ingredient;
import lombok.Data;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("simple")
@Data
public class Taco {

    @NotNull
    @Size(min = 5,message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<Ingredient>ingredients;

}
