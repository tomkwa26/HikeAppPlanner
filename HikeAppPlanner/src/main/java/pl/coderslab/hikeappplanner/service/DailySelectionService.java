package pl.coderslab.hikeappplanner.service;

import org.springframework.stereotype.Service;
import pl.coderslab.hikeappplanner.model.DailySelection;
import pl.coderslab.hikeappplanner.model.Trail;
import pl.coderslab.hikeappplanner.model.TrailCategory;
import pl.coderslab.hikeappplanner.repository.DailySelectionRepository;
import pl.coderslab.hikeappplanner.repository.TrailCategoryRepository;
import pl.coderslab.hikeappplanner.repository.TrailRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DailySelectionService {

    private final DailySelectionRepository selectionRepository;

    private final TrailCategoryRepository categoryRepository;

    private final TrailRepository trailRepository;

    public DailySelectionService(DailySelectionRepository selectionRepository, TrailCategoryRepository categoryRepository, TrailRepository trailRepository) {
        this.selectionRepository = selectionRepository;
        this.categoryRepository = categoryRepository;
        this.trailRepository = trailRepository;
    }

    public void saveCategoryForDailySelection(Long dailySelectionId, Long categoryId) {
        Optional<DailySelection> optionalDailySelection = selectionRepository.findById(dailySelectionId);
        Optional<TrailCategory> optionalTrailCategory = categoryRepository.findById(categoryId);
        DailySelection dailySelection = optionalDailySelection.get();
        TrailCategory trailCategory = optionalTrailCategory.get();

        dailySelection.setCategory(trailCategory);
        selectionRepository.save(dailySelection);
    }

    public void saveTrailForDailySelection(Long dailySelectionId, Long trailId) {
        Optional<DailySelection> optionalDailySelection = selectionRepository.findById(dailySelectionId);
        Optional<Trail> optionalTrail = trailRepository.findById(trailId);
        DailySelection dailySelection = optionalDailySelection.get();
        Trail trail = optionalTrail.get();

        dailySelection.setTrail(trail);
        selectionRepository.save(dailySelection);
    }

    public boolean isLastDayOfHike(Long hikeId, Long dailySelectionId) {
        List<DailySelection> dailySelections = selectionRepository.findAllByHikeId(hikeId);

        // znajdź liczbę dni
        int totalDays = dailySelections.size();

        // znajdź numer aktualnego dnia
        int currentDay = 0;
        for (int i = 0; i < totalDays; i++) {
            if (dailySelections.get(i).getId().equals(dailySelectionId)) {
                currentDay = i + 1;
                break;
            }
        }

        // sprawdzenie, czy aktualny dzień jest ostatnim dniem
        return currentDay == totalDays;
    }
}
