package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ObterMedicoPorIdService {

    private final MedicoGateway medicoRepository;

    public ObterMedicoPorIdService(MedicoGateway cardapioRepository){
        this.medicoRepository = cardapioRepository;
    }

       public Medico findById(Long id){
        return this.medicoRepository.getMedicoById(id);
    }
}

