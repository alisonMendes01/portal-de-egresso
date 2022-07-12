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
@SuppressWarnings({"rawtypes", "unchecked"})
public class CargoController {

    @Autowired
    CargoService service;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody CargoDto dto) {
        /* Montando o cargo que vem do front */
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

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletar(@PathVariable(value = "id", required = true) Integer id) {
        /* Criando o o Cargo para deletar */
        try {
            Cargo cargo = Cargo.builder().id_cargo(id).build();
            service.remover(cargo);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/obter/{id}")
    public ResponseEntity obter(
            @PathVariable(value = "id", required = true) Integer id) {

        Cargo filtro = Cargo.builder().id_cargo(id).build();
        List<Cargo> cargo = service.buscar(filtro);

        return ResponseEntity.ok(cargo);
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity atualizar(
            @PathVariable("id") Integer id_cargo,
            @RequestBody CargoDto dto) {
        try {
            Cargo cargo = Cargo.builder()
                    .id_cargo(id_cargo)
                    .nome(dto.getNome())
                    .descricao(dto.getDescricao())
                    .build();
            Cargo salvo = service.editar(cargo);
            return ResponseEntity.ok(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    
    @GetMapping("/obter-por-egresso/{id}")
    public ResponseEntity obterporegresso(
            @PathVariable("id") Integer id) {
        try{
            List<Cargo> cargo = service.findByEgresso(id);
            return ResponseEntity.ok(cargo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/contar-por-egresso/{id}")
    public ResponseEntity countCargoPorEgresso(
         @PathVariable("id") Integer id
    ){
        try{
            Integer qtdCargos = service.countEgressosByCargo(id);
            return ResponseEntity.ok(qtdCargos);
        } catch (RegraNegocioRunTime e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
