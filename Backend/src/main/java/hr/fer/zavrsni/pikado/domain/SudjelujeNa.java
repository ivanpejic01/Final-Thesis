package hr.fer.zavrsni.pikado.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class SudjelujeNa {

    @EmbeddedId
    private TurnirIgracKljuc id;

    @ManyToOne
    @MapsId("idTurnir")
    @JoinColumn(name= "id_turnir")
    @JsonIgnoreProperties("sudjelujeNa")
    private Turnir turnir;

    @ManyToOne
    @MapsId("idIgrac")
    @JoinColumn(name = "id_igrac")
    @JsonIgnoreProperties("sudjelujeNa")
    private Igrac igrac;

    private Integer plasman;

    public SudjelujeNa() {

    }

    public TurnirIgracKljuc getId() {
        return id;
    }

    public void setId(TurnirIgracKljuc id) {
        this.id = id;
    }

    public Integer getPlasman() {
        return plasman;
    }

    public void setPlasman(Integer plasman) {
        this.plasman = plasman;
    }

    public Turnir getTurnir() {
        return turnir;
    }

    public void setTurnir(Turnir turnir) {
        this.turnir = turnir;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }
}
