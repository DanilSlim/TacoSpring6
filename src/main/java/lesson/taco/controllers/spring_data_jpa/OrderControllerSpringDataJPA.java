package lesson.taco.controllers.spring_data_jpa;


import jakarta.validation.Valid;
import lesson.taco.data.spring_data_jpa.IngredientRefRepositorySpringDataJPA;
import lesson.taco.data.spring_data_jpa.OrderRepositorySpringDataJPA;
import lesson.taco.domain.spring_data_jpa.Ingredient;
import lesson.taco.domain.spring_data_jpa.TacoOrderSpringDataJPA;
import lesson.taco.domain.spring_data_jpa.TacoSpringDataJPA;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Profile("spring_data_jpa")
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderControllerSpringDataJPA {

    private OrderRepositorySpringDataJPA orderRepo;
    private IngredientRefRepositorySpringDataJPA ingredientRefRepo;

    public OrderControllerSpringDataJPA(OrderRepositorySpringDataJPA orderRepo,
                                        IngredientRefRepositorySpringDataJPA ingredientRefRepo) {
        this.orderRepo=orderRepo;
        this.ingredientRefRepo=ingredientRefRepo;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        TacoOrderSpringDataJPA orderInModel = (TacoOrderSpringDataJPA) model.getAttribute("tacoOrder");
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("tacoOrder") TacoOrderSpringDataJPA tacoOrder, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        tacoOrder.setPlacedAt(new Date());
        List<TacoSpringDataJPA> tacos=tacoOrder.getTacos();
        long i=0;
        ArrayList<ArrayList>ingredientsList=new ArrayList<>();
        for (TacoSpringDataJPA taco : tacos) {
            taco.setOrder(tacoOrder);
            taco.setTacoOrderKey(i);
            i++;
            ArrayList<String>ingredientsId=new ArrayList<>();
            for(Ingredient ingredient : taco.getIngredients()) {
                ingredientsId.add(ingredient.getId());
            }
            ingredientsList.add(ingredientsId);
        }
        orderRepo.save(tacoOrder);


        /**
         * Code for correct filling TACO_KEY field in INGREDIENT_REF table
         */
        long tacoId=tacos.get(0).getId();

        int ingredientsListSize=ingredientsList.size();

        for(int j=0; j<ingredientsListSize; j++){

            int ingredientsIdListSize=ingredientsList.get(j).size();

            for(int k=0; k<ingredientsIdListSize; k++){

                ingredientRefRepo.updateIngredientRef((String) ingredientsList.get(j).get(k),j+tacoId,k);
            }


        }


        sessionStatus.setComplete();

        return "redirect:/";
    }


}
