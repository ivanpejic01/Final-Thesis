package hr.fer.zavrsni.pikado.service.impl;

import hr.fer.zavrsni.pikado.dao.DrzavaRepository;
import hr.fer.zavrsni.pikado.domain.Drzava;
import hr.fer.zavrsni.pikado.service.DrzavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrzavaServiceImpl implements DrzavaService {

    @Autowired
    DrzavaRepository drzavaRepository;

    @Override
    public List<Drzava> listAll() {
        return drzavaRepository.findAll();
    }
}
