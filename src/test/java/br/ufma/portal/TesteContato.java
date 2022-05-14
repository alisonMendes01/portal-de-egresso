package br.ufma.portal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.portal.entidades.Contato;
import br.ufma.portal.repository.ContatoRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteContato {
    @Autowired
    ContatoRepo repo;

    @Test
    public void devecriaContato(){
        //cenario
        Contato novo = Contato.builder().nome("teste").url_logo("urlaqui").build();

        // acao
        Contato retorno = repo.save(novo);
        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome()); 

    }
}