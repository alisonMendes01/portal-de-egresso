package br.ufma.portal.model;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.repository.CargoRepo;

@SpringBootTest
public class TesteCargo {
    @Autowired
    public
    CargoRepo repo;

    @Test
    public void devecriaCargo(){
        //cenario
        Cargo novo = Cargo.builder().nome("criarCargo").descricao("descricao").build();

        // acao
        Cargo retorno = repo.save(novo);
        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome());
        Assertions.assertEquals(novo.getDescricao(), retorno.getDescricao());

        //Rollback
        repo.delete(retorno);
    }

    @Test
    public void deveremoverCargo(){
        //cenario
        Cargo novo = Cargo.builder().nome("RemoverCargo").descricao("descricao").build();
        Cargo retorno = repo.save(novo);
        
        // acao
        Integer id = retorno.getId_cargo();
        repo.deleteById(id);
        
        //verificação
        Optional<Cargo> temp = repo.findById(id);  
        Assertions.assertFalse(temp.isPresent());

        //Rollback
        repo.delete(retorno);

    }
    
    @Test
    public void deveobterCargo(){
        //cenario
        Cargo novo = Cargo.builder().nome("ObterCargo").descricao("descricao").build();
        Cargo retorno = repo.save(novo);

        // acao
        Optional<Cargo> temp = repo.findById(retorno.getId_cargo());
        
        // verificacao
        Assertions.assertTrue(temp.isPresent());

        //Rollback
        repo.delete(retorno);

    }
}