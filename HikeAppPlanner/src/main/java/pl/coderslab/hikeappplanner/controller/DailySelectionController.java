package pl.coderslab.hikeappplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.hikeappplanner.model.*;
import pl.coderslab.hikeappplanner.repository.DailySelectionRepository;
import pl.coderslab.hikeappplanner.repository.HikeRepository;
import pl.coderslab.hikeappplanner.repository.TrailCategoryRepository;
import pl.coderslab.hikeappplanner.repository.TrailRepository;
import pl.coderslab.hikeappplanner.service.DailySelectionService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/select")
public class DailySelectionController {

    private final DailySelectionRepository selectionRepository;

    private final DailySelectionService dailySelectionService;

    private final HikeRepository hikeRepository;

    private final TrailCategoryRepository categoryRepository;

    private final TrailRepository trailRepository;

    public DailySelectionController(DailySelectionRepository selectionRepository, DailySelectionService dailySelectionService, HikeRepository hikeRepository, TrailCategoryRepository categoryRepository, TrailRepository trailRepository) {
        this.selectionRepository = selectionRepository;
        this.dailySelectionService = dailySelectionService;
        this.hikeRepository = hikeRepository;
        this.categoryRepository = categoryRepository;
        this.trailRepository = trailRepository;
    }

    @GetMapping("/category")
    public String selectTrailCategory(@RequestParam("hikeId") Long hikeId, Model model) {

        // pobranie pustych wpisów związanych z daną wyprawą
        List<DailySelection> dailySelections = selectionRepository.findAllByHikeId(hikeId);

        // utworzenie listy kategorii szlaków dla dni wycieczki z dodaniem całej listy do kategorii
        List<List<TrailCategory>> categories = new ArrayList<>();

        for (DailySelection dailySelection : dailySelections) {
            Long areaId = dailySelection.getHike().getArea().getId();

            // pobieranie dostępnych kategorii szlaków dla wybranego obszaru
            List<TrailCategory> trailCategoriesList = categoryRepository.findAllByAreas_Id(areaId);
            categories.add(trailCategoriesList);
        }

        // przekazanie danych do modelu
        model.addAttribute("dailySelections", dailySelections);
        model.addAttribute("categories", categories);
        model.addAttribute("hikeId", hikeId);
        return "dailySelects/selectCategoryForm";
    }

    @PostMapping("/category")
    public String saveSelectCategory(@RequestParam("hikeId") Long hikeId,
                                     @RequestParam("dailySelectionId") Long dailySelectionId,
                                     @RequestParam("categoryId") Long categoryId) {

        // zapis wybóru kategorii dla danego dnia wyprawy
        dailySelectionService.saveCategoryForDailySelection(dailySelectionId, categoryId);

        // sprawdzenie, czy jest to ostatni dzień wyprawy
        if (dailySelectionService.isLastDayOfHike(hikeId, dailySelectionId)) {

            // przekierowanie do akcji wyboru szlaku
            return "redirect:/select/trail?hikeId=" + hikeId;
        }

        // powrót na widok wyboru kategorii
        return "redirect:/select/category?hikeId=" + hikeId;
    }

    @GetMapping("/trail")
    public String selectTrail(@RequestParam("hikeId") Long hikeId, Model model) {

        // pobranie pustych wpisów związanych z daną wyprawą
        List<DailySelection> dailySelections = selectionRepository.findAllByHikeId(hikeId);

        // utworzenie mapy przechowującej listę szlaków dla każdego dnia wycieczki
        Map<Long, List<Trail>> trails = new HashMap<>();
        Map<Long, String> errorMessages = new HashMap<>();

        for (DailySelection dailySelection : dailySelections) {
            Long areaId = dailySelection.getHike().getArea().getId();
            Long categoryId = dailySelection.getCategory().getId();

            // pobieranie dostępnych szlaków dla wybranej kategorii szlaków i wybranego obszaru
            List<Trail> trailList = trailRepository.findAllByArea_IdAndCategory_Id(areaId, categoryId);

            if (trailList.isEmpty()) {
                errorMessages.put(dailySelection.getId(), "Nie odnaleziono szlaków dla wybranej kategorii.");
            }
                trails.put(dailySelection.getId(), trailList);
        }

        // przekazanie danych do modelu
        model.addAttribute("dailySelections", dailySelections);
        model.addAttribute("trails", trails);
        model.addAttribute("errorMessages", errorMessages);
        model.addAttribute("hikeId", hikeId);
        return "dailySelects/selectTrailForm";
    }

    @PostMapping("/trail")
    public String saveSelectTrail(@RequestParam("hikeId") Long hikeId,
                                  @RequestParam("dailySelectionId") Long dailySelectionId,
                                  @RequestParam("trailId") Long trailId) {

        // zapis wybóru szlaku dla danego dnia wyprawy
        dailySelectionService.saveTrailForDailySelection(dailySelectionId, trailId);

        // sprawdzenie, czy jest to ostatni dzień wyprawy
        if (dailySelectionService.isLastDayOfHike(hikeId, dailySelectionId)) {

            // przekierowanie do akcji wyboru szlaku
            return "redirect:/summary?hikeId=" + hikeId;
        }

        // powrót na widok wyboru kategorii
        return "redirect:/select/trail?hikeId=" + hikeId;
    }

    @GetMapping("/summary")
    public String showSummary(@RequestParam("hikeId") Long hikeId, Model model) {

        // pobranie wyprawy i powiązanych z nią wyborów
        Hike hike = hikeRepository.findById(hikeId).orElseThrow();
        List<DailySelection> dailySelections = selectionRepository.findAllByHikeId(hikeId);

        // przekazanie danych do modelu
        model.addAttribute("hike", hike);
        model.addAttribute("dailySelections", dailySelections);
        return "dailySelects/summary";
    }
}
