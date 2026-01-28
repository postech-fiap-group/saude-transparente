package com.fiap.saude_transparente.application.controller;

import com.fiap.saude_transparente.application.controller.dtos.CriarMedicoDTO;
import com.fiap.saude_transparente.domain.commands.AlterarMedicoCommand;
import com.fiap.saude_transparente.domain.commands.CriarMedicoCommand;
import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.exceptions.InvalidFieldsException;
import com.fiap.saude_transparente.domain.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medico")
@Tag(name = "Medico", description = "Cadastro de médicos")
@Log4j
public class MedicoController {

    private final CriarMedicoService criarMedicoService;
    private final AlterarMedicoService alterarMedicoService;
    private final DeletarMedicoService deletarMedicoService;
    private final ObterMedicosService obterMedicosService;
    private final ObterMedicoPorIdService obterMedicoPorIdService;
    private final Validator validator;

    public MedicoController(CriarMedicoService criarMedicoService,
                            AlterarMedicoService alterarMedicoService,
                            DeletarMedicoService deletarMedicoService,
                            ObterMedicosService obterMedicosService,
                            ObterMedicoPorIdService obterMedicoPorIdService,
                            Validator validator) {
        this.criarMedicoService = criarMedicoService;
        this.alterarMedicoService = alterarMedicoService;
        this.deletarMedicoService = deletarMedicoService;
        this.obterMedicosService = obterMedicosService;
        this.obterMedicoPorIdService = obterMedicoPorIdService;
        this.validator = validator;
    }

    @Operation(summary = "Listar todos médicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de médicos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<Medico>> findAllMedico(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        var medicos = this.obterMedicosService.findAll(page, size);
        return ResponseEntity.ok(medicos);
    }

    @Operation(summary = "Buscar médico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médicos retornado com sucesso"),
            @ApiResponse(responseCode = "402", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Medico> findMedicoById(@PathVariable("id") Long id) {
        var medico = this.obterMedicoPorIdService.findById(id);
        return ResponseEntity.ok(medico);
    }

    @Operation(summary = "criar cadastro de médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médicos cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campos inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<Void> createMedico(@RequestBody CriarMedicoDTO medicoDTO) {
        var erros = this.validator.validateObject(medicoDTO)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());
        if (!erros.isEmpty())
            throw new InvalidFieldsException(erros);

        var cmd = new CriarMedicoCommand(
                medicoDTO.nome(),
                medicoDTO.sobreNome(),
                medicoDTO.cpf(),
                medicoDTO.crm(),
                medicoDTO.especialidades(),
                medicoDTO.email(),
                medicoDTO.endereco(),
                medicoDTO.telefone(),
                medicoDTO.dataNascimento());
        criarMedicoService.criar(cmd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "atualizar cadastro de médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médicos atualizado com sucesso"),
            @ApiResponse(responseCode = "402", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "400", description = "Campos inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMedico(
            @PathVariable("id") Long id,
            @RequestBody CriarMedicoDTO medicoDTO) {

        var erros = this.validator.validateObject(medicoDTO)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());
        if (!erros.isEmpty())
            throw new InvalidFieldsException(erros);

        var cmd = new AlterarMedicoCommand(id,
                medicoDTO.nome(),
                medicoDTO.sobreNome(),
                medicoDTO.cpf(),
                medicoDTO.crm(),
                medicoDTO.especialidades(),
                medicoDTO.email(),
                medicoDTO.endereco(),
                medicoDTO.telefone(),
                medicoDTO.dataNascimento());
        this.alterarMedicoService.update(cmd);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @Operation(summary = "deletar cadastro de médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médicos deletado com sucesso"),
            @ApiResponse(responseCode = "402", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable("id") Long id) {
        this.deletarMedicoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
