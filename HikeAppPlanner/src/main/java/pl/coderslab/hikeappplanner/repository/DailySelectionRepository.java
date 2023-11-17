package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.hikeappplanner.model.DailySelection;
import pl.coderslab.hikeappplanner.model.Hike;

import java.util.List;

public interface DailySelectionRepository extends JpaRepository<DailySelection, Long> {

    List<DailySelection> findAllByHikeId(Long hikeId);
}
