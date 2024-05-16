package hr.fer.zavrsni.pikado.dto;

public class NadimakDTO {
    private String nadimakPobjednika;

    public NadimakDTO() {
    }

    public NadimakDTO(String nadimakPobjednika) {
        this.nadimakPobjednika = nadimakPobjednika;
    }

    public String getNadimakPobjednika() {
        return nadimakPobjednika;
    }

    public void setNadimakPobjednika(String nadimakPobjednika) {
        this.nadimakPobjednika = nadimakPobjednika;
    }
}
