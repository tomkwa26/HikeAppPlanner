package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.hikeappplanner.model.TrailCategory;

import java.util.List;

public interface TrailCategoryRepository extends JpaRepository<TrailCategory, Long> {

    @Query("SELECT DISTINCT tc FROM TrailCategory tc JOIN tc.areas a WHERE a.id = :areaId")
    List<TrailCategory> findAllByAreaId(@Param("areaId") Long areaId);


}
