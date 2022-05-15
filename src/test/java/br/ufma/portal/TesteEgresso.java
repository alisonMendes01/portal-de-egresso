package br.ufma.portal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.portal.entidades.Egresso;
import br.ufma.portal.repository.EgressoRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteEgresso {
    @Autowired
    public
    EgressoRepo repo;

    @Test
    public void devecriaEgresso(){
        //cenario
        Egresso novo = Egresso.builder().nome("teste").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();

        // acao
        Egresso retorno = repo.save(novo);
        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome()); 

    }
}