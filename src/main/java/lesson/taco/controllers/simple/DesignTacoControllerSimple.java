package lesson.taco.controllers.simple;

import jakarta.validation.Valid;
import lesson.taco.domain.Ingredient;
import lesson.taco.domain.simple.Taco;
import lesson.taco.domain.simple.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Profile("simple")
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoControllerSimple {


    @ModelAttribute
    public void addIngredientsToModel(Model model){

        //list of ingredients records
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        //all types of ingredients
        Ingredient.Type[] types= Ingredient.Type.values();

        for (Ingredient.Type type:types) {

            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients, type));

        }

    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    /* Requests handling */

/* Simple model requests handling */
    @GetMapping()
    public String showDesignForm() {
        return "design";
    }

    @PostMapping()
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){

        if(errors.hasErrors()) return "design";

        tacoOrder.addTaco(taco);

        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    /* ehd simple model requests handling */

}


