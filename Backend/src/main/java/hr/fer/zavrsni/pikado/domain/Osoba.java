package hr.fer.zavrsni.pikado.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CollectionIdMutability;

import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "osoba")
public class Osoba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOsoba")
    private Long id;

    @Column(name = "imeOsoba", nullable = false)
    private String ime;

    @Column(name = "prezimeOsoba", nullable = false)
    private String prezime;


    @Column(name = "oibOsoba", nullable = false, unique = true)
    private String oib;

    @Column(name = "datumRodjenja", nullable = false)
    private LocalDate datumRodenja;

    @Column(name = "spol")
    private String spol;

    @Column(name = "email")
    private String email;

    @Column(name = "brojMobitela")
    private String brojMobitela;

    @OneToOne(mappedBy = "osoba")
    @JsonIgnore
    private Igrac igrac;

    @OneToOne(mappedBy = "osoba")
    @JsonIgnore
    private Organizator organizator;

    public Osoba() {

    }
    public Osoba(String ime, String prezime, String oib, LocalDate datumRodenja, String spol, String email, String brojMobitela) {
        this.ime = ime;
        this.prezime = prezime;
        this.oib = oib;
        this.datumRodenja = datumRodenja;
        this.spol = spol;
        this.email = email;
        this.brojMobitela = brojMobitela;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public LocalDate getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(LocalDate datumRodenja) {
        this.datumRodenja = datumRodenja;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojMobitela() {
        return brojMobitela;
    }

    public void setBrojMobitela(String brojMobitela) {
        this.brojMobitela = brojMobitela;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    public Organizator getOrganizator() {
        return organizator;
    }

    public void setOrganizator(Organizator organizator) {
        this.organizator = organizator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Osoba o = (Osoba) obj;
        return Objects.equals(id, o.id);
    }

    @Override
    public String toString() {
        return this.ime + " " + this.prezime;
    }
}
