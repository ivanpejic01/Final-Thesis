package hr.fer.zavrsni.pikado.dao;

import hr.fer.zavrsni.pikado.domain.Drzava;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrzavaRepository extends JpaRepository<Drzava, Long> {

    Drzava findByNaziv(String naziv);

}
