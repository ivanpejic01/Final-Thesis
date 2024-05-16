package hr.fer.zavrsni.pikado.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "turnir")
public class Turnir {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTurnir")
    private Long id;

    @Column(name = "nazivTurnir")
    private String naziv;

    @Column(name = "datumOdrzavanja")
    private LocalDate datum;

    @Column(name = "vrijemeOdrzavanja")
    private LocalTime vrijeme;

    @Column(name = "nagradniFond")
    private Integer nagradniFond;

    @Column(name = "maxBrojNavijaca")
    private Integer brojNavijaca;

    @Column(name = "maxBrojIgraca")
    private Integer brojIgraca;

    @Column(name = "opis")
    private String opis;

    @ManyToOne
    @JoinColumn(name = "id_vrsta_turnir")
    private VrstaTurnira vrstaTurnira;

    @ManyToOne
    @JoinColumn(name = "id_objekta")
    private Objekt objekt;

    @ManyToMany
    @JoinTable(
            name = "sudjelujeNa",
            joinColumns = @JoinColumn(name = "id_turnir"),
            inverseJoinColumns = @JoinColumn(name = "id_igrac")
    )
    @JsonIgnoreProperties("turniri")
    private List<Igrac> igraci;

    /*@OneToMany(mappedBy = "turnir")
    @JsonIgnoreProperties("turnir")
    private List<SudjelujeNa> sudjelujeNa;*/

   /* @ManyToMany
    @JoinTable(
            name = "organizira",
            joinColumns = @JoinColumn(name = "id_turnir"),
            inverseJoinColumns = @JoinColumn(name = "id_organizator")
    )
    private List<Organizator> organizatori;*/

    @ManyToOne
    @JoinColumn(name = "id_organizator")
    private Organizator organizator;


    public Turnir() {
    }

    public Turnir(String naziv, LocalDate datum, LocalTime vrijeme, Integer nagradniFond, Integer brojNavijaca, Integer brojIgraca, String opis, VrstaTurnira vrstaTurnira, Objekt objekt, List<Igrac> igraci, Organizator organizator) {
        this.naziv = naziv;
        this.datum = datum;
        this.vrijeme = vrijeme;
        this.nagradniFond = nagradniFond;
        this.brojNavijaca = brojNavijaca;
        this.brojIgraca = brojIgraca;
        this.opis = opis;
        this.vrstaTurnira = vrstaTurnira;
        this.objekt = objekt;
        this.igraci = igraci;
        this.organizator = organizator;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(LocalTime vrijeme) {
        this.vrijeme = vrijeme;
    }

    public Integer getNagradniFond() {
        return nagradniFond;
    }

    public void setNagradniFond(Integer nagradniFond) {
        this.nagradniFond = nagradniFond;
    }

    public Integer getBrojNavijaca() {
        return brojNavijaca;
    }

    public void setBrojNavijaca(Integer brojNavijaca) {
        this.brojNavijaca = brojNavijaca;
    }

    public Integer getBrojIgraca() {
        return brojIgraca;
    }

    public void setBrojIgraca(Integer brojIgraca) {
        this.brojIgraca = brojIgraca;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Objekt getObjekt() {
        return objekt;
    }

    public void setObjekt(Objekt objekt) {
        this.objekt = objekt;
    }

    public VrstaTurnira getVrstaTurnira() {
        return vrstaTurnira;
    }

    public void setVrstaTurnira(VrstaTurnira vrstaTurnira) {
        this.vrstaTurnira = vrstaTurnira;
    }

   public List<Igrac> getIgraci() {
        return igraci;
    }

    public void setIgraci(List<Igrac> igraci) {
        this.igraci = igraci;
    }

    public Organizator getOrganizator() {
        return organizator;
    }

    public void setOrganizator(Organizator organizator) {
        this.organizator = organizator;
    }

    @Override
    public String toString() {
        return this.naziv;
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

        Turnir t = (Turnir) obj;
        return Objects.equals(id, t.id);
    }


}
