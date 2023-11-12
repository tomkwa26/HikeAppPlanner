package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.hikeappplanner.model.Area;
import pl.coderslab.hikeappplanner.model.Hike;

import java.util.List;

public interface HikeRepository extends JpaRepository<Hike, Long> {
}
