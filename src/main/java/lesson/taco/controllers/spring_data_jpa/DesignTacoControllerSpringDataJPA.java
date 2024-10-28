package lesson.taco.controllers.spring_data_jpa;


import lesson.taco.data.spring_data_jpa.IngredientRepositorySpringDataJPA;
import lesson.taco.domain.spring_data_jpa.Ingredient;
import lesson.taco.domain.spring_data_jpa.TacoOrderSpringDataJPA;
import lesson.taco.domain.spring_data_jpa.TacoSpringDataJPA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Profile("spring_data_jpa")
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoControllerSpringDataJPA {


    private final IngredientRepositorySpringDataJPA ingredientRepo;



    public DesignTacoControllerSpringDataJPA(IngredientRepositorySpringDataJPA ingredientRepo){

        this.ingredientRepo=ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrderSpringDataJPA order() {
        return new TacoOrderSpringDataJPA();
    }

    @ModelAttribute(name = "taco")
    public TacoSpringDataJPA taco() {
        return new TacoSpringDataJPA();
    }

    /* Requests handling */

    /* JDBC model requests handling */
    @GetMapping()
    public String showDesignForm() {
        return "design";
    }

    @PostMapping()
    public String processTaco(TacoSpringDataJPA taco, Errors errors,
                              @ModelAttribute("tacoOrder") TacoOrderSpringDataJPA tacoOrder){

        if(errors.hasErrors()) return "design";

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Ingredient.Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }
}
