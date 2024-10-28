package lesson.taco.controllers.jdbc_template;

import jakarta.validation.Valid;
import lesson.taco.data.jdbc_template.JdbcOrderRepository;
import lesson.taco.domain.jdbc_template.TacoOrderJDBC;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Profile("jdbc_template")
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderControllerJDBS {

    private JdbcOrderRepository orderRepo;

    public OrderControllerJDBS(JdbcOrderRepository orderRepo) {
        this.orderRepo=orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {

        TacoOrderJDBC orderInModel = (TacoOrderJDBC) model.getAttribute("tacoOrder");
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("tacoOrder") TacoOrderJDBC tacoOrder, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        orderRepo.save(tacoOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }


}
