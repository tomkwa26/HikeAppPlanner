package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.hikeappplanner.model.Area;
import pl.coderslab.hikeappplanner.model.TrailCategory;

import java.util.List;

public interface TrailCategoryRepository extends JpaRepository<TrailCategory, Long> {

    List<TrailCategory> findAllByAreasContains(Area area);

    List<TrailCategory> findAllByAreas_Id(Long areaId);


}
