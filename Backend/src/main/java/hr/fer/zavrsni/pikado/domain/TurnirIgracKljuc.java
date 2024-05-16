package hr.fer.zavrsni.pikado.domain;

import java.io.Serializable;
import java.util.Objects;

public class TurnirIgracKljuc implements Serializable {

    private Long idTurnir;
    private Long idIgrac;

    public Long getIdTurnir() {
        return idTurnir;
    }

    public Long getIdIgrac() {
        return idIgrac;
    }

    public void setIdTurnir(Long idTurnir) {
        this.idTurnir = idTurnir;
    }

    public void setIdIgrac(Long idIgrac) {
        this.idIgrac = idIgrac;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTurnir, idIgrac);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TurnirIgracKljuc kljuc = (TurnirIgracKljuc) obj;
        return ((Objects.equals(idTurnir, kljuc.idTurnir)) &&
                (Objects.equals(idIgrac, kljuc.idIgrac)));
    }
}
