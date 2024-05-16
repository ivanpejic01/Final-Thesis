package hr.fer.zavrsni.pikado.domain;

import java.io.Serializable;
import java.util.Objects;

public class TurnirOrganizatorKljuc implements Serializable {

    private Long idTurnir;
    private Long idOrganizator;

    public TurnirOrganizatorKljuc() {
    }

    public Long getIdTurnir() {
        return idTurnir;
    }

    public void setIdTurnir(Long idTurnir) {
        this.idTurnir = idTurnir;
    }

    public Long getIdOrganizator() {
        return idOrganizator;
    }

    public void setIdOrganizator(Long idOrganizator) {
        this.idOrganizator = idOrganizator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTurnir, idOrganizator);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TurnirOrganizatorKljuc kljuc = (TurnirOrganizatorKljuc) obj;
        return ((Objects.equals(idTurnir, kljuc.idTurnir)) &&
                (Objects.equals(idOrganizator, kljuc.idOrganizator)));
    }

}
