package br.ufma.portal.model;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.repository.EgressoRepo;

@SpringBootTest
public class TesteEgresso {
    @Autowired
    public
    EgressoRepo repo;

    @Test
    public void devecriaEgresso(){
        //cenario
        Egresso novo = Egresso.builder().nome("CriarEgresso").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();

        // acao
        Egresso retorno = repo.save(novo);

        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome());
        Assertions.assertEquals(novo.getCpf(), retorno.getCpf());
        Assertions.assertEquals(novo.getEmail(), retorno.getEmail());
        Assertions.assertEquals(novo.getResumo(), retorno.getResumo());
        //Rollback
        repo.delete(retorno);
    }

    @Test
    public void deveremoverEgresso(){
        //cenario
        Egresso novo = Egresso.builder().nome("RemoverEgresso").cpf("123").email("a@a.com").resumo("resumo abc").build();
        Egresso retorno = repo.save(novo);
        
        // acao
        Integer id = retorno.getId_egresso();
        repo.deleteById(id);
        
        //verificação
        Optional<Egresso> temp = repo.findById(id);  
        Assertions.assertFalse(temp.isPresent());
    }

    @Test
    public void deveobterEgresso(){
        //cenario
        Egresso novo = Egresso.builder().nome("RemoverEgresso").cpf("123").email("a@a.com").resumo("resumo abc").build();
        Egresso retorno = repo.save(novo);

        // acao
        Optional<Egresso> temp = repo.findById(retorno.getId_egresso());
        
        // verificacao
        Assertions.assertTrue(temp.isPresent());

        //Rollback
        repo.delete(retorno);

    }


}