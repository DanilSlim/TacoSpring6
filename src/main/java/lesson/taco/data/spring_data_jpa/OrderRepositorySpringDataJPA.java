package lesson.taco.data.spring_data_jpa;



import lesson.taco.domain.spring_data_jpa.TacoOrderSpringDataJPA;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("spring_data_jpa")
@Repository
public interface OrderRepositorySpringDataJPA extends JpaRepository<TacoOrderSpringDataJPA, Long> {

}
