package br.ufma.portal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.portal.entidades.Depoimento;
import br.ufma.portal.repository.DepoimentoRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteDepoimento {
    @Autowired
    public
    DepoimentoRepo repo;

    @Test
    public void devecriaDepoimento(){
        //cenario
        Depoimento novo = Depoimento.builder().texto("texto").build();

        // acao
        Depoimento retorno = repo.save(novo);
        // verificacao
        Assertions.assertNotNull(retorno);

    }
}