package hr.fer.zavrsni.pikado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "igrac")
public class Igrac{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idIgrac")
    private Long id;

    @Column(name = "nadimak")
    private String nadimak;

    @ManyToOne
    @JoinColumn(name = "id_drzave")
    private Drzava drzava;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "id_osoba")
    private Osoba osoba;

    @JsonIgnoreProperties("igraci")
    @ManyToMany(mappedBy = "igraci")
    private List<Turnir> turniri;


    public Igrac() {

    }

    public Igrac(String nadimak, Drzava drzava, Osoba osoba) {
        this.nadimak = nadimak;
        this.drzava = drzava;
        this.osoba = osoba;
    }

    public Long getId() {
        return id;
    }

    public String getNadimak() {
        return nadimak;
    }

    public void setNadimak(String nadimak) {
        this.nadimak = nadimak;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

  public List<Turnir> getTurniri() {
        return turniri;
    }

   public void setTurniri(List<Turnir> turniri) {
        this.turniri = turniri;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    /*public List<SudjelujeNa> getSudjelujeNa() {
        return sudjelujeNa;
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Igrac o = (Igrac) obj;
        return Objects.equals(id, o.id);
    }

    @Override
    public String toString() {
        return this.getOsoba().getIme() + " " + this.getOsoba().getPrezime();
    }
}
