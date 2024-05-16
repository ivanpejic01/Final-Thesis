package hr.fer.zavrsni.pikado.service.impl;

import hr.fer.zavrsni.pikado.dao.OrganizatorRepository;
import hr.fer.zavrsni.pikado.domain.Igrac;
import hr.fer.zavrsni.pikado.domain.Organizator;
import hr.fer.zavrsni.pikado.service.OrganizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizatorServiceImpl implements OrganizatorService {

    @Autowired
    OrganizatorRepository organizatorRepository;

    @Override
    public List<Organizator> listAll() {
        return organizatorRepository.findAll();
    }

    @Override
    public Optional<Organizator> findById(Long id) {
        return organizatorRepository.findById(id);
    }
}
