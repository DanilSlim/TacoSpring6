package lesson.taco.data.spring_data_jdbc;


import lesson.taco.domain.spring_data_jdbc.TacoOrderSpringDataJDBC;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;



@Profile("spring_data_jdbc")
public interface OrderRepositorySpringDataJDBC extends CrudRepository<TacoOrderSpringDataJDBC, Long> {

}
