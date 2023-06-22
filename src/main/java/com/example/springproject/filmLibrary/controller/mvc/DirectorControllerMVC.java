package com.example.springproject.filmLibrary.controller.mvc;

import com.example.springproject.filmLibrary.dto.DirectorDTO;
import com.example.springproject.filmLibrary.service.DirectorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/directors")
public class DirectorControllerMVC {

    private final DirectorService directorService;

    public DirectorControllerMVC(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public String getAllDirectors(Model model) {
        model.addAttribute("directors", directorService.listAll());
        return "directors/viewAllDirectors";
    }

    @GetMapping("/{id}")
    public String getOneDirector(@PathVariable("id") Long id, Model model) {
        model.addAttribute("director", directorService.getOne(id));
        model.addAttribute("films", directorService.getFilmsOfDirector(id));
        return "directors/viewOne";
    }

    @GetMapping("/add")
    public String addDirector(Model model) {
        DirectorDTO dto = new DirectorDTO();
        model.addAttribute("directorDTO", dto);
        return "directors/addDirector";
    }

    @PostMapping("/add")
    public String createDirector(@ModelAttribute("directorDTO") DirectorDTO directorDTO) {
        directorService.create(directorDTO);
        return "redirect:/directors";
    }

}


