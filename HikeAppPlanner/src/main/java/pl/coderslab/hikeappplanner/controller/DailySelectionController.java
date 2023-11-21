package pl.coderslab.hikeappplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.hikeappplanner.model.*;
import pl.coderslab.hikeappplanner.repository.DailySelectionRepository;
import pl.coderslab.hikeappplanner.repository.HikeRepository;
import pl.coderslab.hikeappplanner.repository.TrailCategoryRepository;
import pl.coderslab.hikeappplanner.repository.TrailRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/select")
public class DailySelectionController {

    private final DailySelectionRepository selectionRepository;

    private final HikeRepository hikeRepository;

    private final TrailCategoryRepository categoryRepository;

    private final TrailRepository trailRepository;

    public DailySelectionController(DailySelectionRepository selectionRepository, HikeRepository hikeRepository,
                                    TrailCategoryRepository categoryRepository, TrailRepository trailRepository) {
        this.selectionRepository = selectionRepository;
        this.hikeRepository = hikeRepository;
        this.categoryRepository = categoryRepository;
        this.trailRepository = trailRepository;
    }

    @GetMapping("/category")
    public String selectTrailCategory(@RequestParam("hikeId") Long hikeId, Model model) {

        // pobranie pustych wpisów związanych z daną wyprawą
        List<DailySelection> dailySelections = selectionRepository.findAllByHikeId(hikeId);

        // pobranie dostępnych kategorii dla wybranej area
        List<TrailCategory> categories = categoryRepository.findAllByAreas_Id(dailySelections.get(0).getHike().getArea().getId());

        // przekazanie danych do modelu
        model.addAttribute("dailySelections", dailySelections);
        model.addAttribute("categories", categories);
        model.addAttribute("hikeId", hikeId);
        return "dailySelects/selectCategoryForm";
    }

    @PostMapping("/category")
    public String saveSelectCategory(@RequestParam("hikeId") Long hikeId,
                                     @ModelAttribute DailySelection dailySelection) {

        // zapisanie wyborów kategorii dla poszczególnych dni
        selectionRepository.save(dailySelection);

        // przekierowanie na widok wyboru kategorii
        return "redirect:/select/trail?hikeId=" + hikeId;
    }

    @GetMapping("/trail")
    public String selectTrail(@RequestParam("hikeId") Long hikeId, Model model) {

        // pobranie pustych wpisów związanych z daną wyprawą
        List<DailySelection> dailySelections = selectionRepository.findAllByHikeId(hikeId);

        // pobieranie dostępnych szlaków dla wybranej kategorii szlaków
        List<Trail> trails = trailRepository.findAllByCategoryInAndArea(dailySelections.stream()
                .map(DailySelection::getCategory)
                .collect(Collectors.toList()), dailySelections.get(0).getHike().getArea());

        // przekazanie danych do modelu
        model.addAttribute("dailySelections", dailySelections);
        model.addAttribute("trails", trails);
        model.addAttribute("hikeId", hikeId);
        return "dailySelects/selectTrailForm";
    }

    @PostMapping("/trail")
    public String saveSelectTrail(@RequestParam("hikeId") Long hikeId, @ModelAttribute("dailySelections") List<DailySelection> dailySelections) {

        // zapisanie wyborów kategorii dla poszczególnych dni
        selectionRepository.saveAll(dailySelections);

        // przekierowanie na widok podsumowania
        return "redirect:/select/summary?hikeId=" + hikeId;
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
