package lesson.taco.domain.spring_data_jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Profile("spring_data_jpa")
@Data
@Entity
@Table(name="TACO")
public class TacoSpringDataJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt =new Date();

    @NotNull
    @Size(min = 5,message = "Name must be at least 5 characters long")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TACO_ORDER")
    private TacoOrderSpringDataJPA order;

    @Column(name = "TACO_ORDER_KEY")
    private Long tacoOrderKey;

    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    @ManyToMany
    @JoinTable(name = "INGREDIENT_REF",
               joinColumns = @JoinColumn(name="TACO"),
               inverseJoinColumns = @JoinColumn(name="INGREDIENT"))
    private List<Ingredient> ingredients=new ArrayList<>();

    public void addIngredient (Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
