package hr.fer.zavrsni.pikado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "organizator")
public class Organizator{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOrganizator")
    private Long id;
    @Column(name = "organizirani_turniri")
    private Integer brojTurnira;

   /* @JsonIgnoreProperties("organizatori")
    @ManyToMany(mappedBy = "organizatori")
    private List<Turnir> turniri;*/

    @OneToOne
    @JsonIgnoreProperties("organizator")
    @JoinColumn(name = "id_osoba")
    private Osoba osoba;

    @OneToMany(mappedBy = "organizator")
    @JsonIgnoreProperties("organizator")
    private List<Turnir> turniri;


    public Organizator() {

    }


    public Organizator(Integer brojTurnira, List<Turnir> turniri, Osoba osoba) {
        this.brojTurnira = brojTurnira;
        this.osoba = osoba;
        this.turniri = turniri;
    }

    public Long getId() {
        return id;
    }

    public Integer getBrojTurnira() {
        return brojTurnira;
    }

    public void setBrojTurnira(Integer brojTurnira) {
        this.brojTurnira = brojTurnira;
    }


    public Osoba getOsoba() {
        return osoba;
    }


    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public List<Turnir> getTurniri() {
        return turniri;
    }

    public void setTurniri(List<Turnir> turniri) {
        this.turniri = turniri;
    }
}
