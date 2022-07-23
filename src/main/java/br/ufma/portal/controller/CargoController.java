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
import br.ufma.portal.model.dto.CargoDto;
import br.ufma.portal.service.CargoService;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@RestController
@RequestMapping("/api/cargo")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CargoController {

    @Autowired
    CargoService service;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody CargoDto dto) {
        Cargo cargo = Cargo.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();
        try {
            Cargo salvo = service.salvar(cargo);
            return new ResponseEntity(salvo, HttpStatus.CREATED);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity remover(@PathVariable(value = "id", required = true) Integer id) {
        try {
            Cargo cargo = Cargo.builder().id_cargo(id).build();
            service.remover(cargo);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscar(
            @PathVariable(value = "id", required = true) Integer id) {

        Cargo filtro = Cargo.builder().id_cargo(id).build();
        List<Cargo> cargo = service.buscar(filtro);

        return ResponseEntity.ok(cargo);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(
            @PathVariable("id") Integer id_cargo,
            @RequestBody CargoDto dto) {
        try {
            Cargo cargo = Cargo.builder()
                    .id_cargo(id_cargo)
                    .nome(dto.getNome())
                    .descricao(dto.getDescricao())
                    .build();
            Cargo salvo = service.atualizar(cargo);
            return ResponseEntity.ok(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar-por-egresso/{id}")
    public ResponseEntity buscarPorEgresso(
            @PathVariable("id") Integer id) {
        try {
            List<Cargo> cargo = service.buscarPorEgresso(id);
            return ResponseEntity.ok(cargo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/contar-egressos-por-cargo/{id}")
    public ResponseEntity contarEgressosPorCargo(
            @PathVariable("id") Integer id) {
        try {
            Integer qtdCargos = service.contarEgressosPorCargo(id);
            return ResponseEntity.ok(qtdCargos);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
