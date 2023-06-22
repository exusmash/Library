package com.example.springproject.filmLibrary.controller.mvc;

import com.example.springproject.filmLibrary.dto.DirectorDTO;
import com.example.springproject.filmLibrary.dto.FilmDTO;
import com.example.springproject.filmLibrary.service.DirectorService;
import com.example.springproject.filmLibrary.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/films")
public class FilmControllerMVC {

    private final FilmService filmService;

    private final DirectorService directorService;

    public FilmControllerMVC(FilmService filmService, DirectorService directorService) {
        this.filmService = filmService;
        this.directorService = directorService;
    }

    @GetMapping
    public String getAllFilms(Model model) {
        model.addAttribute("films", filmService.listAll());
        return "films/viewAllFilms";
    }

    @GetMapping("/{id}")
    public String getOneFilm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("film", filmService.getOne(id));
        model.addAttribute("directors", directorService.listAll());
        model.addAttribute("director", new DirectorDTO());
        return "films/viewOne";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        FilmDTO dto = new FilmDTO();
        model.addAttribute("filmDTO", dto);
        return "films/addFilm";
    }

    @PostMapping("/add")
    public String createFilm(@ModelAttribute("filmDTO") FilmDTO filmDTO) {
        filmService.create(filmDTO);
        return "redirect:/films";
    }

    @PostMapping("/addDirector/{id}")
    public String addDirectorToFilm(@ModelAttribute("director") DirectorDTO directorDTO, @PathVariable("id") Long id) {
        FilmDTO filmToUpdate = filmService.getOne(id);
        filmToUpdate.getDirectorIds().add(directorDTO.getId());
        filmService.update(filmToUpdate);
        return "redirect:/films";
    }
}
