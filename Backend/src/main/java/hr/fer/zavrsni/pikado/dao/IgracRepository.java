package hr.fer.zavrsni.pikado.dao;

import hr.fer.zavrsni.pikado.domain.Igrac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IgracRepository extends JpaRepository<Igrac, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Igrac i WHERE i.nadimak = :nadimak")
    boolean postojiIgrac(String nadimak);

    Optional<Igrac> findByNadimak(String nadimakPobjednika);

}
