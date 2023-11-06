package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.hikeappplanner.model.Area;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    @Override
    List<Area> findAll();
}
