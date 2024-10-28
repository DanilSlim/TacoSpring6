package lesson.taco.controllers.jdbc_template;

import lesson.taco.data.jdbc_template.IngredientRepository;
import lesson.taco.domain.*;
import lesson.taco.domain.jdbc_template.TacoJDBC;
import lesson.taco.domain.jdbc_template.TacoOrderJDBC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Profile("jdbc_template")
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoControllerJDBC {


    private final IngredientRepository ingredientRepo;



    public DesignTacoControllerJDBC(IngredientRepository ingredientRepo){

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
    public TacoOrderJDBC order() {
        return new TacoOrderJDBC();
    }

    @ModelAttribute(name = "taco")
    public TacoJDBC taco() {
        return new TacoJDBC();
    }

    /* Requests handling */

    /* JDBC model requests handling */
    @GetMapping()
    public String showDesignForm() {
        return "design";
    }

    @PostMapping()
    public String processTaco(TacoJDBC taco, Errors errors, @ModelAttribute("tacoOrder") TacoOrderJDBC tacoOrder){

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
