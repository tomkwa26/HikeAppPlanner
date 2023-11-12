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

    public HikeController(HikeRepository hikeRepository, AreaRepository areaRepository) {
        this.hikeRepository = hikeRepository;
        this.areaRepository = areaRepository;
    }

    @ModelAttribute("areas")
    public List<Area> areas() {
        return areaRepository.findAll();
    }

    @GetMapping("/create")
    public String showHikeForm(Model model) {
        model.addAttribute("hike", new Hike());
        model.addAttribute("areas", areas());
        return "/hikes/createHikeForm";
    }

    @PostMapping("/create")
    public String createHike(@Valid Hike hike, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/hikes/createHikeForm";
        }

        // zapisanie wyprawy
        hikeRepository.save(hike);

        // uzyskanie wybranych dat i obszaru
        LocalDate startDate = hike.getStartDate();
        LocalDate endDate = hike.getEndDate();

        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

//        // stworzenie obiektu DailySelection i przypisanie wyprawy
//        DailySelection dailySelection = new DailySelection();
//        dailySelection.setHike(hike);

        // przekazanie danych do widoku
        redirectAttributes.addFlashAttribute("dates", dates);

        // przekierowanie na widok
        return "redirect:/select/category";
    }
}
