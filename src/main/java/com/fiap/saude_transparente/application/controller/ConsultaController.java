package com.fiap.saude_transparente.application.controller;

import com.fiap.saude_transparente.application.controller.dtos.CriarConsultaDTO;
import com.fiap.saude_transparente.domain.commands.AlterarConsultaCommand;
import com.fiap.saude_transparente.domain.commands.CriarConsultaCommand;
import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.usecases.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consultas")
@AllArgsConstructor
@Tag(name = "Consultas", description = "Consultas de pacientes")
public class ConsultaController {
    private final CriarConsultaService criarConsultaService;
    private final AlterarConsultaService alterarConsultaService;
    private final ObterConsultasService obterConsultasService;
    private final ObterConsultaPorIdService obterConsultaPorIdService;
    private final DeletarConsultaService deletarConsultaService;
    private Validator validator;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid CriarConsultaDTO dto) {
        var erros = this.validator.validateObject(dto)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());

        this.criarConsultaService.save(new CriarConsultaCommand(
                dto.pacienteId(),
                dto.medicoId(),
                dto.dataConsulta(),
                dto.motivo()
        ));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(
            @PathVariable("id") Long id,
            @RequestBody CriarConsultaDTO dto) {

        var erros = this.validator.validateObject(dto)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());

        var cmd = new AlterarConsultaCommand(
                id,
                dto.pacienteId(),
                dto.medicoId(),
                dto.dataConsulta(),
                dto.motivo()
        );

        this.alterarConsultaService.save(cmd);

        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(this.obterConsultasService.execute(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getById(
            @PathVariable("id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(this.obterConsultaPorIdService.execute(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id) {
        this.deletarConsultaService.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
