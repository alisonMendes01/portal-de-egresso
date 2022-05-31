package br.ufma.portal.entidade;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.portal.entidade.repository.ContatoRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteContato {
    @Autowired
    public
    ContatoRepo repo;

    @Test
    public void devecriaContato(){
        //cenario
        Contato novo = Contato.builder().nome("CriarContato").url_logo("urlaqui").build();

        // acao
        Contato retorno = repo.save(novo);
        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome());
        
        //Rollback
        repo.delete(retorno);
    }
    
    @Test
    public void deveremoverContato(){
        //cenario
        Contato novo = Contato.builder().nome("RemoverContato").url_logo("urlaqui").build();
        Contato retorno = repo.save(novo);
        
        // acao
        Integer id = retorno.getId_contato();
        repo.deleteById(id);
        
        //verificação
        Optional<Contato> temp = repo.findById(id);  
        Assertions.assertFalse(temp.isPresent());

    }
    
    @Test
    public void deveobterContato(){
        //cenario
        Contato novo = Contato.builder().nome("ObterCOntato").url_logo("urlaqui").build();
        Contato retorno = repo.save(novo);

        // acao
        Optional<Contato> temp = repo.findById(retorno.getId_contato());
        // verificacao

        Assertions.assertTrue(temp.isPresent());

        //Rollback
        repo.delete(retorno);

    }
}