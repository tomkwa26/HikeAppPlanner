package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.hikeappplanner.model.Hike;

public interface HikeRepository extends JpaRepository<Hike, Long> {
}
