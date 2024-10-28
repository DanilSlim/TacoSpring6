package lesson.taco.data.jdbc_template;

import lesson.taco.domain.jdbc_template.TacoOrderJDBC;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Profile("jdbc_template")
public interface OrderRepositoryJDBC {

    TacoOrderJDBC save(TacoOrderJDBC order);

    Optional<TacoOrderJDBC> findById(Long id);
}
