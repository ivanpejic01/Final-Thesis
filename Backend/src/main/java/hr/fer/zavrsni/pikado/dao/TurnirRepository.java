package hr.fer.zavrsni.pikado.dao;

import hr.fer.zavrsni.pikado.domain.Objekt;
import hr.fer.zavrsni.pikado.domain.Turnir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurnirRepository extends JpaRepository<Turnir, Long> {

    boolean existsById(Long id);



}
