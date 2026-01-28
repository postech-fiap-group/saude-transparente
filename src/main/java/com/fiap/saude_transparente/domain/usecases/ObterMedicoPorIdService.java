package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Medico;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ObterMedicoPorIdService {


    public Medico findById(Long id) {
        return null;
    }
}
