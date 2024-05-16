package hr.fer.zavrsni.pikado.service;

import hr.fer.zavrsni.pikado.domain.Drzava;
import hr.fer.zavrsni.pikado.domain.Igrac;
import hr.fer.zavrsni.pikado.domain.Osoba;

import java.util.List;
import java.util.Optional;

public interface IgracService {

    List<Igrac> listAll();
    Optional<Igrac> findById(Long id);

    Igrac noviIgrac(Osoba osoba, String nadimak, Drzava drzava);


    Igrac dohvatiIgraca(Long id);
}
