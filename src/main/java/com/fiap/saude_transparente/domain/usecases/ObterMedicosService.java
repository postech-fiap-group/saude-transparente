package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Medico;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ObterMedicosService {
    public List<Medico> findAll(int page, int size) {
        return null;
    }
}
