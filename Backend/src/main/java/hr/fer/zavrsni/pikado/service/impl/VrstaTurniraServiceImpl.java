package hr.fer.zavrsni.pikado.service.impl;


import hr.fer.zavrsni.pikado.dao.VrstaTurniraRepository;
import hr.fer.zavrsni.pikado.domain.VrstaTurnira;
import hr.fer.zavrsni.pikado.service.VrstaTurniraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VrstaTurniraServiceImpl implements VrstaTurniraService {

    @Autowired
    VrstaTurniraRepository vrstaTurniraRepository;

    @Override
    public List<VrstaTurnira> listAll() {
        return vrstaTurniraRepository.findAll();
    }

    @Override
    public Optional<VrstaTurnira> findById(Long id) {
        return vrstaTurniraRepository.findById(id);
    }
}
