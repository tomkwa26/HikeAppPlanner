package pl.coderslab.hikeappplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.hikeappplanner.model.DailySelection;

public interface DailySelectionRepository extends JpaRepository<DailySelection, Long> {
}
