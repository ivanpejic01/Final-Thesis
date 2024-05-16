package hr.fer.zavrsni.pikado.rest;

import hr.fer.zavrsni.pikado.domain.Drzava;
import hr.fer.zavrsni.pikado.service.DrzavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping
public class DrzavaController {

    @Autowired
    DrzavaService drzavaService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/drzave")
    public List<Drzava> listaDrzave() {
        return drzavaService.listAll();
    }
}
