package hr.fer.zavrsni.pikado.service.impl;

import hr.fer.zavrsni.pikado.dao.IgracRepository;
import hr.fer.zavrsni.pikado.domain.Drzava;
import hr.fer.zavrsni.pikado.domain.Igrac;
import hr.fer.zavrsni.pikado.domain.Osoba;
import hr.fer.zavrsni.pikado.service.IgracService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IgracServiceImpl implements IgracService {

    @Autowired
    IgracRepository igracRepository;

    @Override
    public List<Igrac> listAll() {
        return igracRepository.findAll();
    }

    @Override
    public Optional<Igrac> findById(Long id) {
        return igracRepository.findById(id);
    }

    @Override
    public Igrac noviIgrac(Osoba osoba, String nadimak, Drzava drzava) {
        if (osoba == null) {
            throw new RuntimeException("Nemoguce kreirati igraca!");
        }

        Igrac igrac = igracRepository.findByNadimak(nadimak).orElse(null);
        if(igrac == null) {
            igrac = new Igrac();
            igrac.setNadimak(nadimak);
            igrac.setDrzava(drzava);
            igrac.setOsoba(osoba);
        }

        return igracRepository.save(igrac);

    }

    @Override
    public Igrac dohvatiIgraca(Long id) {
        Igrac igrac = igracRepository.findById(id).orElse(null);
        if (igrac == null) {
            return new Igrac();
        }

        return igrac;
    }
}
