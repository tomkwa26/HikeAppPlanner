package pl.coderslab.hikeappplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return "hikes/createHikeForm";
    }

    @PostMapping("/create")
    public String createHike(@Valid Hike hike, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "hikes/createHikeForm";
        }

        // uzyskanie wybranych dat
        LocalDate startDate = hike.getStartDate();
        LocalDate endDate = hike.getEndDate();

        // sprawdzenie, czy wybrana data znajduje się w poprawnym przedziale (ograniczenie API)
        // dla darmowej usługi ForecastApi to max. 4 dni z dzisiejszym
        LocalDate minDate = LocalDate.now().plusDays(0); // dla płatnej usługi FutureApi to 14 dni
        LocalDate maxDate = LocalDate.now().plusDays(3); // dla płatnej usługi FutureApi to 300 dni

        if (startDate.isBefore(minDate) || endDate.isAfter(maxDate)) {
            model.addAttribute("invalidDateMessage", "Wybierz datę między " + minDate + " a " + maxDate);
            return "hikes/createHikeForm";
        }

        // zapisanie wyprawy
        Hike savedHike = hikeRepository.save(hike);

        // generowanie pustych wpisów dla każdego dnia wyprawy
        List<DailySelection> dailySelections = new ArrayList<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            DailySelection dailySelection = new DailySelection(savedHike, null, null, currentDate);
            dailySelections.add(dailySelection);
            currentDate = currentDate.plusDays(1);
        }

        // zapisanie pustych wpisów do bazy danych
        selectionRepository.saveAll(dailySelections);

        // przekierowanie na widok wyboru kategorii szlaków z przekazaniem parametru
        return "redirect:/select/category?hikeId=" + savedHike.getId();
    }
}
