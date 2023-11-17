package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.hikeappplanner.model.Area;
import pl.coderslab.hikeappplanner.model.Trail;
import pl.coderslab.hikeappplanner.model.TrailCategory;

import java.util.List;

public interface TrailRepository extends JpaRepository<Trail, Long> {

    List<Trail> findAllByCategoryInAndArea(List<TrailCategory> categories, Area area);
}
