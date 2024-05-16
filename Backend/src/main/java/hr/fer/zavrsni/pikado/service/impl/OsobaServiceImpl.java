package hr.fer.zavrsni.pikado.service.impl;

import hr.fer.zavrsni.pikado.dao.IgracRepository;
import hr.fer.zavrsni.pikado.dao.OrganizatorRepository;
import hr.fer.zavrsni.pikado.dao.OsobaRepository;
import hr.fer.zavrsni.pikado.domain.Organizator;
import hr.fer.zavrsni.pikado.domain.Osoba;
import hr.fer.zavrsni.pikado.dto.OsobaRequestDTO;
import hr.fer.zavrsni.pikado.dto.PostojeciDTO;
import hr.fer.zavrsni.pikado.service.OsobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OsobaServiceImpl implements OsobaService {

    @Autowired
    private OsobaRepository osobaRepository;

    @Autowired
    private IgracRepository igracRepository;

    @Autowired
    private OrganizatorRepository organizatorRepository;

    @Override
    public List<Osoba> listAll() {
        return osobaRepository.findAll();
    }

    @Override
    public Optional<Osoba> findById(Long id) {
        return osobaRepository.findById(id);
    }

    @Override
    public Osoba ubaciIgraca(OsobaRequestDTO osobaZahtjev) throws Exception {


        Osoba osoba = osobaRepository.findByOib(osobaZahtjev.getOib());
        if (osoba == null) {
            osoba.setIme(osobaZahtjev.getIme());
            osoba.setPrezime(osobaZahtjev.getPrezime());
            osoba.setOib(osobaZahtjev.getOib());
            osoba.setDatumRodenja(osobaZahtjev.getDatumRodenja());
            osoba.setSpol(osobaZahtjev.getSpol());
            osoba.setEmail(osobaZahtjev.getEmail());
            osoba.setBrojMobitela(osobaZahtjev.getBrojMobitela());
        }
       /* else {
            if (osoba.getIgrac() != null) {
                if (!osoba.getIgrac().getNadimak().equals(osobaZahtjev.getNadimak())) {
                    System.out.println("Nadimak osobe = " + osoba.getIgrac().getNadimak() + " nadimak zahtjeva " + osobaZahtjev.getNadimak());
                    throw new Exception ("Igrač s nadimkom postoji");
                }
            }
        }*/
        return osobaRepository.save(osoba);
    }

    @Override
    public Osoba ubaciPostojecegIgraca(PostojeciDTO postojeciIgrac) throws Exception {
        Osoba osoba = osobaRepository.findByOib(postojeciIgrac.getOib());
        if (osoba == null) {
            throw new Exception("Ne postoji osoba s primljenim oibom");
        }
        else {
            if (!osoba.getIgrac().getNadimak().equals(postojeciIgrac.getNadimak())) {
                throw new Exception("Igrac je mapiran s drugim nadimkom");
            }
        }

        return osobaRepository.save(osoba);
    }

    @Override
    public List<Osoba> sviOrganizatori() {
        List<Osoba> organizatoriOsobe = new ArrayList<>();
        List<Organizator> organizatori = organizatorRepository.findAll();


        for(Organizator organizator: organizatori) {
                organizatoriOsobe.add(organizator.getOsoba());
        }

        return organizatoriOsobe;
    }

    @Override
    public Osoba nadiOsobuPoImenuIPrezimenu(String ime, String prezime) {
        List<Osoba> osobe = osobaRepository.findAll();
        Osoba trazenaOsoba = osobaRepository.findByImeAndPrezime(ime, prezime);
        /*for(Osoba osoba: osobe) {
            if (osoba.getIme().equals(imeOrganizatora) && osoba.getPrezime().equals(prezimeOrganizatora)) {
                trazenaOsoba = osoba;
            }
        }*/

        return trazenaOsoba;
    }
}
