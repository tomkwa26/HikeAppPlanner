package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.hikeappplanner.model.Trail;
import pl.coderslab.hikeappplanner.model.TrailCategory;

import java.util.List;

public interface TrailRepository extends JpaRepository<Trail, Long> {

    List<Trail> findAllByCategory(TrailCategory category);
}
