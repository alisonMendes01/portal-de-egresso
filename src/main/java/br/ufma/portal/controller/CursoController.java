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

import br.ufma.portal.model.Curso;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.dto.CursoDto;
import br.ufma.portal.service.CursoService;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/curso")
public class CursoController {
    @Autowired
    private CursoService service;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody CursoDto dto) {
        Curso curso = Curso.builder()
                .nome(dto.getNome())
                .nivel(dto.getNivel())
                .build();
        try {
            Curso salvo = service.salvar(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity remover(@PathVariable(value = "id", required = true) Integer id) {
        try {
            Curso curso = Curso.builder().id_curso(id).build();
            service.remover(curso);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscar(
            @PathVariable(value = "id", required = true) Integer id) {
        try {
            Curso filtro = Curso.builder().id_curso(id).build();
            List<Curso> cargo = service.buscar(filtro);
            return ResponseEntity.ok(cargo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(
            @PathVariable("id") Integer id,
            @RequestBody CursoDto dto) {
        try {
            Curso curso = Curso.builder()
                    .id_curso(id)
                    .nome(dto.getNome())
                    .nivel(dto.getNivel())
                    .build();
            Curso salvo = service.atualizar(curso);
            return ResponseEntity.ok(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar-por-egresso/{id}")
    public ResponseEntity buscarPorEgresso(
            @PathVariable("id") Integer id) {
        try {
            List<Egresso> egresso = service.buscarPorEgresso(id);
            return ResponseEntity.ok(egresso);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/contar-egressos-por-curso/{id}")
    public ResponseEntity contarEgressosPorCurso(
            @PathVariable("id") Integer id) {
        try {
            Integer qtdEgressos = service.contarEgressosPorCurso(id);
            return ResponseEntity.ok(qtdEgressos);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
