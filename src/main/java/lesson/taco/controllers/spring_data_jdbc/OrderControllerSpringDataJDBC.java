package lesson.taco.controllers.spring_data_jdbc;

import jakarta.validation.Valid;
import lesson.taco.data.spring_data_jdbc.OrderRepositorySpringDataJDBC;
import lesson.taco.domain.spring_data_jdbc.TacoOrderSpringDataJDBC;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

@Profile("spring_data_jdbc")
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderControllerSpringDataJDBC {

    private OrderRepositorySpringDataJDBC orderRepo;

    public OrderControllerSpringDataJDBC(OrderRepositorySpringDataJDBC orderRepo) {
        this.orderRepo=orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        TacoOrderSpringDataJDBC orderInModel = (TacoOrderSpringDataJDBC) model.getAttribute("tacoOrder");
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("tacoOrder") TacoOrderSpringDataJDBC tacoOrder, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        tacoOrder.setPlacedAt(new Date());
        orderRepo.save(tacoOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }


}
