package hr.fer.zavrsni.pikado.dao;

import hr.fer.zavrsni.pikado.domain.Osoba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Long> {

    boolean existsById(Long id);

    Osoba findByImeAndPrezime(String ime, String prezime);

    Osoba findByOib(String oib);
}
