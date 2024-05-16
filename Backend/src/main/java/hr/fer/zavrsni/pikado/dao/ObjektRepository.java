package hr.fer.zavrsni.pikado.dao;

import hr.fer.zavrsni.pikado.domain.Objekt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjektRepository extends JpaRepository<Objekt, Long> {

    @Override
    boolean existsById(Long integer);

    Objekt findByNazivObjekt(String nazivObjekt);
}
