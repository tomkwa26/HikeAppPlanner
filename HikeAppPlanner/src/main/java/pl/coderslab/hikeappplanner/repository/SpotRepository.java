package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.hikeappplanner.model.Spot;
import pl.coderslab.hikeappplanner.model.Trail;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {

    List<Spot> findAllByTrail(Trail trail);
}
