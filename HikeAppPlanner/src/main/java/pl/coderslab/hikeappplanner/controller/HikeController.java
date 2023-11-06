package pl.coderslab.hikeappplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.hikeappplanner.model.Area;
import pl.coderslab.hikeappplanner.model.Hike;
import pl.coderslab.hikeappplanner.repository.AreaRepository;
import pl.coderslab.hikeappplanner.repository.HikeRepository;

import javax.validation.Valid;
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

    @GetMapping
    public String showHikeForm(Model model) {
        model.addAttribute("hike", new Hike());
        model.addAttribute("areas", areas());
        return "hikes/form";
    }

    @PostMapping
    public String createHike(@Valid Hike hike, BindingResult result) {
        if (result.hasErrors()) {
            return "hikes/form";
        }
        hikeRepository.save(hike);
        return "redirect:/selections/create";
    }
}
