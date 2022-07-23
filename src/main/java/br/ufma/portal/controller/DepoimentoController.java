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

import br.ufma.portal.model.Depoimento;
import br.ufma.portal.model.dto.DepoimentoDto;
import br.ufma.portal.model.dto.EgressoDepoimentoDto;
import br.ufma.portal.service.DepoimentoService;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoService service;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody DepoimentoDto dto) {
        Depoimento depoimento = Depoimento.builder()
                .texto(dto.getTexto())
                .data(dto.getData())
                .build();
        try {
            Depoimento salvo = service.salvar(depoimento);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody DepoimentoDto dto) {
        Depoimento depoimento = Depoimento.builder()
                .id_depoimento(id)
                .texto(dto.getTexto())
                .data(dto.getData())
                .build();
        try {
            Depoimento salvo = service.atualizar(depoimento);
            return ResponseEntity.status(HttpStatus.OK).body(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity remover(@PathVariable(value = "id", required = true) Integer id) {
        try {
            Depoimento depoimento = Depoimento.builder().id_depoimento(id).build();
            service.remover(depoimento);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscar(
            @PathVariable(value = "id", required = true) Integer id) {

        Depoimento filtro = Depoimento.builder().id_depoimento(id).build();
        List<Depoimento> depoimentos = service.buscar(filtro);

        return ResponseEntity.ok(depoimentos);
    }

    @GetMapping("/buscar-por-egresso/{id}")
    public ResponseEntity buscarPorEgresso(
            @PathVariable("id") Integer id) {
        try {
            List<EgressoDepoimentoDto> cargo = service.buscarPorEgresso(id);
            return ResponseEntity.ok(cargo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar-recente")
    public ResponseEntity buscarRecentes() {
        try {
            List<Depoimento> recentes = service.buscarRecente();
            return ResponseEntity.ok(recentes);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
