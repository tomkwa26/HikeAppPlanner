package pl.coderslab.hikeappplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.hikeappplanner.model.*;
import pl.coderslab.hikeappplanner.repository.DailySelectionRepository;
import pl.coderslab.hikeappplanner.repository.HikeRepository;
import pl.coderslab.hikeappplanner.repository.TrailCategoryRepository;
import pl.coderslab.hikeappplanner.repository.TrailRepository;

import java.time.LocalDate;
import java.util.List;

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
    public String selectTrailCategory(@ModelAttribute("dates") List<LocalDate> dates,
                                      Model model) {
        List<TrailCategory> categories = categoryRepository.findAll();
        model.addAttribute("selection", new DailySelection());
        model.addAttribute("categories", categories);
        model.addAttribute("dates", dates);
        return "selections/selectCategoryForm";
    }

    @PostMapping("/category")
    public String saveSelectCategory(@ModelAttribute("selection") DailySelection dailySelection, RedirectAttributes redirectAttributes) {
        selectionRepository.save(dailySelection);
        redirectAttributes.addFlashAttribute("selection", dailySelection);
        return "redirect:/select/trail";
    }

    @GetMapping("/trail")
    public String selectTrail(@ModelAttribute("selection") DailySelection dailySelection, Model model) {
        List<Trail> trails = trailRepository.findTrailsByAreaAndCategory(dailySelection.getHike().getArea(), dailySelection.getCategory());
        model.addAttribute("trails", trails);
        return "selections/selectTrailForm";
    }

    @PostMapping("/trail")
    public String saveSelectTrail(@ModelAttribute DailySelection dailySelection) {
        selectionRepository.save(dailySelection);
        return "redirect:/summary";
    }
}
