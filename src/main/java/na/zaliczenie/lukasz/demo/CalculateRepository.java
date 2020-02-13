package na.zaliczenie.lukasz.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalculateRepository extends CrudRepository<CalculateModel, Long> {
    @Query("SELECT * FROM Obliczenia")
    List<CalculateModel> findAll();
}
