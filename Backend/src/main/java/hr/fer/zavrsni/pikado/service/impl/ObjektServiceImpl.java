package hr.fer.zavrsni.pikado.service.impl;

import hr.fer.zavrsni.pikado.dao.ObjektRepository;
import hr.fer.zavrsni.pikado.domain.Objekt;
import hr.fer.zavrsni.pikado.service.ObjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjektServiceImpl implements ObjektService {

    @Autowired
    ObjektRepository objektRepository;

    @Override
    public List<Objekt> listAll() {
        return objektRepository.findAll();
    }

    @Override
    public Optional<Objekt> findById(Long id) {
        return objektRepository.findById(id);
    }
}
