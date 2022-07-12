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

import br.ufma.portal.model.FaixaSalario;
import br.ufma.portal.model.dto.FaixaSalarioDto;
import br.ufma.portal.service.FaixaSalarioService;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/faixa_salario")
public class FaixaSalarioController {

    @Autowired
    private FaixaSalarioService service;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody FaixaSalarioDto dto) {
        FaixaSalario faixaSalario = FaixaSalario.builder()
                .descricao(dto.getDescricao())
                .build();
        try {
            FaixaSalario salvo = service.salvar(faixaSalario);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletar(@PathVariable(value = "id", required = true) Integer id) {
        /* Criando o o Egresso para deletar */
        try {
            service.remover(id);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter/{id}")
    public ResponseEntity obter(

            @PathVariable(value = "id", required = true) Integer id) {
        FaixaSalario filtro = FaixaSalario.builder()
                .id_faixa_salario(id)
                .build();
        List<FaixaSalario> faixaSalario = service.buscar(filtro);

        return ResponseEntity.ok(faixaSalario);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity atualizar(
            @PathVariable("id") Integer id_cargo,
            @RequestBody FaixaSalarioDto dto) {
        try {
            FaixaSalario faixaSalario = FaixaSalario.builder()
                .descricao(dto.getDescricao())
                .build();
            FaixaSalario salvo = service.editar(faixaSalario);
            return ResponseEntity.ok(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

