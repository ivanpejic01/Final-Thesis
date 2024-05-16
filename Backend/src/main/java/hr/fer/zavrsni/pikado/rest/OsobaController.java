package hr.fer.zavrsni.pikado.rest;

import hr.fer.zavrsni.pikado.dao.DrzavaRepository;
import hr.fer.zavrsni.pikado.dao.IgracRepository;
import hr.fer.zavrsni.pikado.dao.TurnirRepository;
import hr.fer.zavrsni.pikado.domain.Drzava;
import hr.fer.zavrsni.pikado.domain.Igrac;
import hr.fer.zavrsni.pikado.domain.Osoba;
import hr.fer.zavrsni.pikado.domain.Turnir;
import hr.fer.zavrsni.pikado.dto.OsobaRequestDTO;
import hr.fer.zavrsni.pikado.dto.PostojeciDTO;
import hr.fer.zavrsni.pikado.service.IgracService;
import hr.fer.zavrsni.pikado.service.OsobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping

public class OsobaController {

    @Autowired
    private OsobaService osobaService;
    @Autowired
    private IgracService igracService;

    @Autowired
    private DrzavaRepository drzavaRepository;

    @Autowired
    private TurnirRepository turnirRepository;
    @Autowired
    private IgracRepository igracRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/osobe")
    public List<Osoba> listaOsobe() {
        return osobaService.listAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/zahtjev/{id}")
    public List<Osoba> ubaciIgraca(@RequestBody OsobaRequestDTO osobaZahtjev, @PathVariable("id") Long id) throws Exception {
        Osoba osoba = osobaService.ubaciIgraca(osobaZahtjev);
        Drzava drzava = drzavaRepository.findByNaziv(osobaZahtjev.getDrzava());
        Igrac igrac = igracService.noviIgrac(osoba, osobaZahtjev.getNadimak(), drzava);
        Turnir turnir = turnirRepository.findById(id).orElse(null);
        turnir.getIgraci().add(igrac);
        turnirRepository.save(turnir);
        return osobaService.listAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/postojeci/{id}")
    public List<Osoba> ubaciPostojecegIgraca(@RequestBody PostojeciDTO postojeciIgrac, @PathVariable("id") Long id) throws Exception {
        Osoba osoba = osobaService.ubaciPostojecegIgraca(postojeciIgrac);
        Igrac igrac = igracRepository.findByNadimak(postojeciIgrac.getNadimak()).orElse(null);
        Turnir turnir = turnirRepository.findById(id).orElse(null);
        turnir.getIgraci().add(igrac);
        turnirRepository.save(turnir);
        return osobaService.listAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/organizatori")
    public List<Osoba> listaOrganizatori() {
        return osobaService.sviOrganizatori();
    }


}
