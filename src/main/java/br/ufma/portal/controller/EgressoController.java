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

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletar(@PathVariable(value = "id", required = true) Integer id) {
        /* Criando o o Egresso para deletar */
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

    @GetMapping("/obter/{id}")
    public ResponseEntity obter(
        @PathVariable(value = "id", required = true) Integer id) {
        Egresso filtro = Egresso.builder()
                .id_egresso(id)
                .build();
        List<Egresso> egresso = service.buscar(filtro);

        return ResponseEntity.ok(egresso);
    }

    @PutMapping("/editar/{id}")
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
            Egresso salvo = service.editar(egresso);
            return ResponseEntity.ok(salvo);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar/contato/{id_egresso}&{id_contato}")
    public ResponseEntity editarContato(
        @PathVariable("id_egresso") Integer id_egresso,
        @PathVariable("id_contato") Integer id_contato,
        @RequestBody Contato contato)
    {
        try{
            Contato contatoRetorno = service.editarEgressoContato(id_egresso, id_contato, contato);
            return ResponseEntity.ok(contatoRetorno);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar/cargo/{id_egresso}&{id_cargo}")
    public ResponseEntity editarCargo(
        @PathVariable("id_egresso") Integer id_egresso,
        @PathVariable("id_cargo") Integer id_cargo,
        @RequestBody Cargo cargo){
        try{
            Cargo cargoRetorno = service.editarEgressoCargo(id_egresso, id_cargo, cargo);
            return ResponseEntity.ok(cargoRetorno);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar/faixasalario/{id_egresso}&{id_faixasalario}")
    public ResponseEntity editarFaixaSalario(
        @PathVariable("id_egresso") Integer id_egresso,
        @PathVariable("id_faixasalario") Integer id_faixasalario,
        @RequestBody FaixaSalario faixaSalario ){
        
         try{
            FaixaSalario faixaSalarioRetorno = service.editarEgressoFaixaSalario(id_egresso, id_faixasalario, faixaSalario);
            return ResponseEntity.ok(faixaSalarioRetorno);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar/curso/{id_egresso}&{id_curso}")
    public ResponseEntity editarCurso(
        @PathVariable("id_egresso") Integer id_egresso,
        @PathVariable("id_curso") Integer id_curso,
        @RequestBody Curso curso ){
        
         try{
            Curso cursoRetorno = service.editarEgressoCurso(id_egresso, id_curso, curso);
            return ResponseEntity.ok(cursoRetorno);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
