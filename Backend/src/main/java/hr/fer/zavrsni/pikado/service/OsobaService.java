package hr.fer.zavrsni.pikado.service;

import hr.fer.zavrsni.pikado.domain.Osoba;
import hr.fer.zavrsni.pikado.dto.OsobaRequestDTO;
import hr.fer.zavrsni.pikado.dto.PostojeciDTO;

import java.util.List;
import java.util.Optional;

public interface OsobaService {

    List<Osoba> listAll();

    Optional<Osoba> findById(Long id);

    Osoba ubaciIgraca(OsobaRequestDTO osobaZahtjev) throws Exception;

    List<Osoba> sviOrganizatori();

    Osoba nadiOsobuPoImenuIPrezimenu(String imeOrganizatora, String prezimeOrganizatora);

    Osoba ubaciPostojecegIgraca(PostojeciDTO postojeciIgrac) throws Exception;
}
