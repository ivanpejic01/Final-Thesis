package hr.fer.zavrsni.pikado.service.impl;

import hr.fer.zavrsni.pikado.dao.GradRepository;
import hr.fer.zavrsni.pikado.domain.Grad;
import hr.fer.zavrsni.pikado.service.GradService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradServiceImpl implements GradService {

    @Autowired
    GradRepository gradRepository;

    @Override
    public List<Grad> listAll() {
        return gradRepository.findAll();
    }
}
