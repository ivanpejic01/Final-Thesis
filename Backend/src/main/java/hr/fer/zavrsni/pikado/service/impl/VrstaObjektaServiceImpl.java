package hr.fer.zavrsni.pikado.service.impl;

import hr.fer.zavrsni.pikado.dao.VrstaObjektaRepository;
import hr.fer.zavrsni.pikado.domain.VrstaObjekta;
import hr.fer.zavrsni.pikado.service.VrstaObjektaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VrstaObjektaServiceImpl implements VrstaObjektaService {

    @Autowired
    VrstaObjektaRepository vrstaObjektaRepository;

    @Override
    public List<VrstaObjekta> listAll() {
        return vrstaObjektaRepository.findAll();
    }

    @Override
    public Optional<VrstaObjekta> findById(Long id) {
        return vrstaObjektaRepository.findById(id);
    }
}
