package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.hikeappplanner.model.Area;
import pl.coderslab.hikeappplanner.model.Trail;
import pl.coderslab.hikeappplanner.model.TrailCategory;

import java.util.List;

public interface TrailRepository extends JpaRepository<Trail, Long> {

    @Query("SELECT t FROM Trail t WHERE t.area = :area AND t.category = :category")
    List<Trail> findTrailsByAreaAndCategory(@Param("area") Area area, @Param("category") TrailCategory category);
}
