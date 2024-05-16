package hr.fer.zavrsni.pikado.dao;

import hr.fer.zavrsni.pikado.domain.Organizator;
import hr.fer.zavrsni.pikado.domain.Osoba;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizatorRepository extends JpaRepository<Organizator, Long> {


    Organizator findByOsoba(Osoba osoba);
}
