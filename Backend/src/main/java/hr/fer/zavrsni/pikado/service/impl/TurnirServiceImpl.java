package hr.fer.zavrsni.pikado.service.impl;

import hr.fer.zavrsni.pikado.dao.*;
import hr.fer.zavrsni.pikado.domain.*;
import hr.fer.zavrsni.pikado.dto.NadimakDTO;
import hr.fer.zavrsni.pikado.dto.TurnirRequestDTO;
import hr.fer.zavrsni.pikado.dto.TurnirUrediDTO;
import hr.fer.zavrsni.pikado.service.ObjektService;
import hr.fer.zavrsni.pikado.service.OsobaService;
import hr.fer.zavrsni.pikado.service.TurnirService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class TurnirServiceImpl implements TurnirService {

    @Autowired
    TurnirRepository turnirRepository;

    @Autowired
    ObjektService objektService;

    @Autowired
    SudjelujeNaRepository sudjelujeNaRepository;

    @Autowired
    IgracRepository igracRepository;

    @Autowired
    OsobaRepository osobaRepository;

    @Autowired
    OsobaService osobaService;

    @Autowired
    VrstaTurniraRepository vrstaTurniraRepository;

    @Autowired
    ObjektRepository objektRepository;

    @Autowired
    OrganizatorRepository organizatorRepository;

    @Override
    public List<Turnir> listAll() {

        LocalDate danasnjiDatum = LocalDate.now();
        List<Turnir> sviTurniri =  turnirRepository.findAll();
        List<Turnir> filtriraniTurniri = new ArrayList<>();
        for (Turnir mojTurnir: sviTurniri) {
            if (mojTurnir.getDatum().isAfter(danasnjiDatum) || mojTurnir.getDatum().isEqual(danasnjiDatum)) {
                filtriraniTurniri.add(mojTurnir);
            }
        }
        return filtriraniTurniri;
    }

    @Override
    public Optional<Turnir> findById(Long id) {
        return turnirRepository.findById(id);
    }

    @Override
    public List<Turnir> odrzaniTurniri() {

        LocalDate danasnjiDatum = LocalDate.now();
        List<Turnir> sviTurniri =  turnirRepository.findAll();
        List<Turnir> filtriraniTurniri = new ArrayList<>();
        for (Turnir mojTurnir: sviTurniri) {
            if (mojTurnir.getDatum().isBefore(danasnjiDatum)) {
                filtriraniTurniri.add(mojTurnir);
            }
        }

        return filtriraniTurniri;
    }

    @Override
    public Igrac dohvatiPobjednika(Long id) {

        List<SudjelujeNa> sviSudionici = sudjelujeNaRepository.findAll();
        SudjelujeNa pobjednik = new SudjelujeNa();

        for (SudjelujeNa sudionik: sviSudionici) {
            if (sudionik.getId().getIdTurnir() == id) {
                if ((sudionik.getPlasman() != null) && (sudionik.getPlasman() == 1)) {
                    pobjednik = sudionik;
                }
            }
        }
    if (pobjednik.getId() != null) {
        Igrac igracPobjednik = igracRepository.findById(pobjednik.getId().getIdIgrac()).orElse(null);
        return igracPobjednik;
    }
    return new Igrac();
    }

    @Override
    public List<Igrac> igraciTurnir(Long id) {
        Turnir turnir = turnirRepository.findById(id).orElse(null);
        List<Igrac> igraci = turnir.getIgraci();

        return igraci;
    }

        @Override
        public Turnir ubaciTurnir(TurnirRequestDTO turnirRequestDTO) {

        Turnir turnir = new Turnir();
        turnir.setNaziv(turnirRequestDTO.getNazivTurnira());
        turnir.setDatum(turnirRequestDTO.getDatumOdrzavanja());
        if (turnirRequestDTO.getBrojNavijaca() == "") {
            turnir.setBrojNavijaca(null);
        }
        else {
            turnir.setBrojNavijaca(Integer.parseInt(turnirRequestDTO.getBrojNavijaca()));
        }
        turnir.setBrojIgraca(Integer.parseInt(turnirRequestDTO.getBrojIgraca()));

        if (turnirRequestDTO.getOpis() == "") {
            turnir.setOpis(null);
        }
        else {
            turnir.setOpis(turnirRequestDTO.getOpis());
        }
        turnir.setVrijeme(LocalTime.parse(turnirRequestDTO.getVrijemeOdrzavanja()));
        turnir.setIgraci(new ArrayList<>());
        if (turnirRequestDTO.getNagradniFond() == "") {
            turnir.setNagradniFond(null);
        }
        else {
            turnir.setNagradniFond(Integer.parseInt(turnirRequestDTO.getNagradniFond()));
        }

        turnir.setVrstaTurnira(vrstaTurniraRepository.findByNazivVrsta(turnirRequestDTO.getVrstaTurnira()));
        String ime = turnirRequestDTO.getImeOrganizatora().split(" ")[0];
        String prezime = turnirRequestDTO.getImeOrganizatora().split(" ")[1];
        System.out.println(ime + " " + prezime);
        Osoba osoba = osobaService.nadiOsobuPoImenuIPrezimenu(ime, prezime);
        Organizator organizatorTurnira = organizatorRepository.findByOsoba(osoba);
        if (organizatorTurnira == null) {
            organizatorTurnira = new Organizator();
            organizatorTurnira.setOsoba(osoba);
            organizatorRepository.save(organizatorTurnira);
        }
        turnir.setOrganizator(organizatorTurnira);
        turnir.setObjekt(objektRepository.findByNazivObjekt(turnirRequestDTO.getNazivObjekta()));
        turnirRepository.save(turnir);
        return turnir;
    }

    @Override
    public List<Turnir> sviTurniri() {
        return turnirRepository.findAll();
    }

    @Override
    public Turnir urediTurnir(Long id, TurnirUrediDTO turnirUrediDTO) {
        Turnir turnir = turnirRepository.findById(id).orElse(null);

        turnir.setDatum(turnirUrediDTO.getDatumOdrzavanja());
        turnir.setVrijeme(LocalTime.parse(turnirUrediDTO.getVrijemeOdrzavanja()));
        turnir.setObjekt(objektRepository.findByNazivObjekt(turnirUrediDTO.getNazivObjekta()));
        return turnirRepository.save(turnir);

    }

    @Override
    public Turnir pobjednikNaTurniru(Long id, NadimakDTO nadimakPobjednika) {

        System.out.println("nadimak pobjednika je " + nadimakPobjednika.getNadimakPobjednika());

        Igrac pobjednik = igracRepository.findByNadimak(nadimakPobjednika.getNadimakPobjednika()).orElse(null);
        System.out.println("Id pobjednika je " + pobjednik.getId());

        SudjelujeNa sudjelujeNa = new SudjelujeNa();
        TurnirIgracKljuc kljuc = new TurnirIgracKljuc();
        kljuc.setIdTurnir(id);
        kljuc.setIdIgrac(pobjednik.getId());
        sudjelujeNa.setId(kljuc);
        sudjelujeNa.setIgrac(pobjednik);
        sudjelujeNa.setTurnir(turnirRepository.findById(id).orElse(null));
        sudjelujeNa.setPlasman(1);
        sudjelujeNaRepository.save(sudjelujeNa);

        return turnirRepository.findById(id).orElse(null);

    }

    @Override
    public Map<Igrac, Integer> brojPobjeda() {

        List<SudjelujeNa> sudionici = sudjelujeNaRepository.findAll();
        Map<Igrac, Integer> plasmani = new TreeMap<>(new IgracComparator());

        for (SudjelujeNa sudionik: sudionici) {
            if (sudionik.getPlasman() != null) {
                Igrac igrac = igracRepository.findById(sudionik.getId().getIdIgrac()).orElse(null);
                Integer pobjede = plasmani.get(igrac);
                if (pobjede == null) {
                    pobjede = 0;
                }

                pobjede++;
                plasmani.put(igrac, pobjede);

            }
        }
        return plasmani;
    }

    @Override
    public Map<Turnir, Map<String, Integer>> zeneMuskarci() {
        Map<Turnir, Map<String, Integer>> mapa = new HashMap<>();
        List<Turnir> sviTurniri = turnirRepository.findAll();
        List<Turnir> filtriraniTurniri = new ArrayList<>();
        LocalDate danasnjiDatum = LocalDate.now();
        for (Turnir mojTurnir: sviTurniri) {
            if (mojTurnir.getDatum().isBefore(danasnjiDatum)) {
                filtriraniTurniri.add(mojTurnir);
            }
        }

        for (Turnir turnir: filtriraniTurniri) {
            Map<String, Integer> pomMapa = new HashMap<>();
            List<Igrac> igraci = turnir.getIgraci();
            for(Igrac igrac: igraci) {
                String spol = igrac.getOsoba().getSpol();
                Integer brojPojava = pomMapa.get(spol);
                if (brojPojava == null) {
                    brojPojava = 0;
                }
                brojPojava++;
                pomMapa.put(spol, brojPojava);
            }
            mapa.put(turnir, new HashMap<>(pomMapa));
        }

        return mapa;
    }

    @Override
    public Map<String, Integer> turniriMjeseci() {
        Map<String, Integer> mapa = new LinkedHashMap<>();
        Map<Integer, Integer> pomMapa = new LinkedHashMap<>();
        Map<String, Integer> mjesecBroj = new LinkedHashMap<>();
        mjesecBroj.put("Siječanj", 1);
        mjesecBroj.put("Veljača", 2);
        mjesecBroj.put("Ožujak", 3);
        mjesecBroj.put("Travanj", 4);
        mjesecBroj.put("Svibanj", 5);
        mjesecBroj.put("Lipanj", 6);
        mjesecBroj.put("Srpanj", 7);
        mjesecBroj.put("Kolovoz", 8);
        mjesecBroj.put("Rujan", 9);
        mjesecBroj.put("Listopad", 10);
        mjesecBroj.put("Studeni", 11);
        mjesecBroj.put("Prosinac", 12);

        Map<Integer, String> zeljenaMapa = new LinkedHashMap<>();
        for(Map.Entry<String, Integer> entry: mjesecBroj.entrySet()) {
            zeljenaMapa.put(entry.getValue(), entry.getKey());
        }
        List<Turnir> sviTurniri = turnirRepository.findAll();
        for (Map.Entry<Integer, String> entry: zeljenaMapa.entrySet()) {
            pomMapa.put(entry.getKey(), 0);
        }
        for (Turnir turnir: sviTurniri) {
            Integer broj = turnir.getDatum().getMonth().getValue();
            Integer pojave = pomMapa.get(broj);
            if (pojave == null) {
                pojave = 0;
            }
            pojave++;
            pomMapa.put(broj, pojave);
        }

        for (Map.Entry<Integer, Integer> entry: pomMapa.entrySet()) {
            mapa.put(zeljenaMapa.get(entry.getKey()), entry.getValue());
        }

        return mapa;
    }
}
