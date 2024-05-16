package hr.fer.zavrsni.pikado.rest;


import hr.fer.zavrsni.pikado.domain.Igrac;
import hr.fer.zavrsni.pikado.domain.Osoba;
import hr.fer.zavrsni.pikado.service.IgracService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class IgracController {

    @Autowired
    IgracService igracService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/igraci")
    public List<Igrac> listaIgraci() {
        return igracService.listAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/igrac/{id}")
    public Igrac dohvatiIgraca(@PathVariable("id") Long id) {
        return igracService.dohvatiIgraca(id);
    }
}
