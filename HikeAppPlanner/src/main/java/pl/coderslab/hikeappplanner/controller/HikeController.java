package pl.coderslab.hikeappplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.hikeappplanner.model.Area;
import pl.coderslab.hikeappplanner.model.DailySelection;
import pl.coderslab.hikeappplanner.model.Hike;
import pl.coderslab.hikeappplanner.repository.AreaRepository;
import pl.coderslab.hikeappplanner.repository.DailySelectionRepository;
import pl.coderslab.hikeappplanner.repository.HikeRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hike")
public class HikeController {

    private final HikeRepository hikeRepository;
    private final AreaRepository areaRepository;
    private final DailySelectionRepository selectionRepository;

    public HikeController(HikeRepository hikeRepository, AreaRepository areaRepository, DailySelectionRepository selectionRepository) {
        this.hikeRepository = hikeRepository;
        this.areaRepository = areaRepository;
        this.selectionRepository = selectionRepository;
    }

    @ModelAttribute("areas")
    public List<Area> areas() {
        return areaRepository.findAll();
    }

    @GetMapping("/create")
    public String showHikeForm(Model model) {

        // przekazanie danych do modelu
        model.addAttribute("hike", new Hike());
        model.addAttribute("areas", areas());

        // przekierowanie na widok tworzenia wyprawy
        return "/hikes/createHikeForm";
    }

    @PostMapping("/create")
    public String createHike(@Valid Hike hike, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/hikes/createHikeForm";
        }

        // zapisanie wyprawy
        Hike savedHike = hikeRepository.save(hike);

        // uzyskanie wybranych dat
        LocalDate startDate = savedHike.getStartDate();
        LocalDate endDate = savedHike.getEndDate();

        // generowanie pustych wpisów dla każdego dnia wyprawy
        List<DailySelection> selections = new ArrayList<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            DailySelection dailySelection = new DailySelection(savedHike, null, null, currentDate);
            selections.add(dailySelection);
            currentDate = currentDate.plusDays(1);
        }

        // zapisanie pustych wpisów do bazy danych
        selectionRepository.saveAll(selections);

        // przekierowanie na widok wyboru kategorii szlaków z przekazaniem parametru
        return "redirect:/select/category?hikeId=" + savedHike.getId();
    }
}
