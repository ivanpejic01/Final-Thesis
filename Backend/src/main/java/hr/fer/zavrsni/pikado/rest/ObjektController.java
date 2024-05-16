package hr.fer.zavrsni.pikado.rest;

import hr.fer.zavrsni.pikado.dao.ObjektRepository;
import hr.fer.zavrsni.pikado.domain.Objekt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ObjektController {

    @Autowired
    ObjektRepository objektRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/objekti")
    public List<Objekt> listaObjekata() {return objektRepository.findAll();};


}
