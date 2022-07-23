package br.ufma.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.portal.model.Cargo;
import br.ufma.portal.model.Contato;
import br.ufma.portal.model.Curso;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.FaixaSalario;
import br.ufma.portal.model.dto.EgressoDto;
import br.ufma.portal.service.EgressoService;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/egresso")
public class EgressoController {

    @Autowired
    private EgressoService service;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody EgressoDto dto) {
        Egresso egresso = Egresso.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .url_foto(dto.getUrl_foto())
                .build();
        try {
            Egresso salvo = service.salvar(egresso);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity remover(@PathVariable(value = "id", required = true) Integer id) {
        try {
            Egresso egresso = Egresso.builder()
                    .id_egresso(id)
                    .build();
            service.remover(egresso);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscar(
            @PathVariable(value = "id", required = true) Integer id) {
        Egresso filtro = Egresso.builder()
                .id_egresso(id)
                .build();
        List<Egresso> egresso = service.buscar(filtro);

        return ResponseEntity.ok(egresso);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(
            @PathVariable("id") Integer id_cargo,
            @RequestBody EgressoDto dto) {
        try {
            Egresso egresso = Egresso.builder()
                    .nome(dto.getNome())
                    .email(dto.getEmail())
                    .cpf(dto.getCpf())
                    .resumo(dto.getResumo())
                    .url_foto(dto.getUrl_foto())
                    .build();
            Egresso salvo = service.atualizar(egresso);
            return ResponseEntity.ok(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/contato/{id_egresso}&{id_contato}")
    public ResponseEntity atualizarContato(
            @PathVariable("id_egresso") Integer id_egresso,
            @PathVariable("id_contato") Integer id_contato,
            @RequestBody Contato contato) {
        try {
            Contato contatoRetorno = service.atualizarEgressoContato(id_egresso, id_contato, contato);
            return ResponseEntity.ok(contatoRetorno);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/cargo/{id_egresso}&{id_cargo}")
    public ResponseEntity atualizarCargo(
            @PathVariable("id_egresso") Integer id_egresso,
            @PathVariable("id_cargo") Integer id_cargo,
            @RequestBody Cargo cargo) {
        try {
            Cargo cargoRetorno = service.atualizarEgressoCargo(id_egresso, id_cargo, cargo);
            return ResponseEntity.ok(cargoRetorno);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/faixasalario/{id_egresso}&{id_faixasalario}")
    public ResponseEntity atualizarFaixaSalario(
            @PathVariable("id_egresso") Integer id_egresso,
            @PathVariable("id_faixasalario") Integer id_faixasalario,
            @RequestBody FaixaSalario faixaSalario) {

        try {
            FaixaSalario faixaSalarioRetorno = service.atualizarEgressoFaixaSalario(id_egresso, id_faixasalario,
                    faixaSalario);
            return ResponseEntity.ok(faixaSalarioRetorno);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/curso/{id_egresso}&{id_curso}")
    public ResponseEntity atualizarCurso(
            @PathVariable("id_egresso") Integer id_egresso,
            @PathVariable("id_curso") Integer id_curso,
            @RequestBody Curso curso) {

        try {
            Curso cursoRetorno = service.atualizarEgressoCurso(id_egresso, id_curso, curso);
            return ResponseEntity.ok(cursoRetorno);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
