package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.hikeappplanner.model.TrailCategory;

import java.util.List;

public interface TrailCategoryRepository extends JpaRepository<TrailCategory, Long> {

    @Override
    List<TrailCategory> findAll();
}
