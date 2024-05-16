package hr.fer.zavrsni.pikado.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hr.fer.zavrsni.pikado.domain.Organizator;
import hr.fer.zavrsni.pikado.domain.Osoba;
import hr.fer.zavrsni.pikado.domain.VrstaTurnira;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class TurnirRequestDTO {

    private String nazivTurnira;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate datumOdrzavanja;

    private String vrijemeOdrzavanja;

    private String nagradniFond;

    private String brojIgraca;

    private String brojNavijaca;

    private String opis;

    private String vrstaTurnira;

    private String nazivObjekta;

    private String imeOrganizatora;


    public TurnirRequestDTO() {
    }

    public TurnirRequestDTO(String nazivTurnira, LocalDate datumOdrzavanja, String vrijemeOdrzavanja, String nagradniFond, String brojIgraca, String brojNavijaca, String opis, String vrstaTurnira, String nazivObjekta, String imeOrganizatora) {
        this.nazivTurnira = nazivTurnira;
        this.datumOdrzavanja = datumOdrzavanja;
        this.vrijemeOdrzavanja = vrijemeOdrzavanja;
        this.nagradniFond = nagradniFond;
        this.brojIgraca = brojIgraca;
        this.brojNavijaca = brojNavijaca;
        this.opis = opis;
        this.vrstaTurnira = vrstaTurnira;
        this.nazivObjekta = nazivObjekta;
        this.imeOrganizatora = imeOrganizatora;
    }

    public String getNazivTurnira() {
        return nazivTurnira;
    }

    public void setNazivTurnira(String nazivTurnira) {
        this.nazivTurnira = nazivTurnira;
    }

    public LocalDate getDatumOdrzavanja() {
        return datumOdrzavanja;
    }

    public void setDatumOdrzavanja(LocalDate datumOdrzavanja) {
        this.datumOdrzavanja = datumOdrzavanja;
    }

    public String getVrijemeOdrzavanja() {
        return vrijemeOdrzavanja;
    }

    public void setVrijemeOdrzavanja(String vrijemeOdrzavanja) {
        this.vrijemeOdrzavanja = vrijemeOdrzavanja;
    }

    public String getNagradniFond() {
        return nagradniFond;
    }

    public void setNagradniFond(String nagradniFond) {
        this.nagradniFond = nagradniFond;
    }

    public String getBrojIgraca() {
        return brojIgraca;
    }

    public void setBrojIgraca(String brojIgraca) {
        this.brojIgraca = brojIgraca;
    }

    public String getBrojNavijaca() {
        return brojNavijaca;
    }

    public void setBrojNavijaca(String brojNavijaca) {
        this.brojNavijaca = brojNavijaca;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getVrstaTurnira() {
        return vrstaTurnira;
    }

    public void setVrstaTurnira(String vrstaTurnira) {
        this.vrstaTurnira = vrstaTurnira;
    }

    public String getNazivObjekta() {
        return nazivObjekta;
    }

    public void setNazivObjekta(String nazivObjekta) {
        this.nazivObjekta = nazivObjekta;
    }

    public String getImeOrganizatora() {
        return imeOrganizatora;
    }

    public void setImeOrganizatora(String imeOrganizatora) {
        this.imeOrganizatora = imeOrganizatora;
    }
}
