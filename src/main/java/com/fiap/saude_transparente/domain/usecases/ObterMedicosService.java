package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ObterMedicosService {

    private final MedicoGateway medicoRepository;

    public ObterMedicosService(MedicoGateway cardapioRepository){
        this.medicoRepository = cardapioRepository;
    }

    public List<Medico> findAll(int page, int size){
        int offset = (page-1) * size;
        return this.medicoRepository.getAllMedicos(size,offset);
    }

}
