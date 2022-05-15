package br.ufma.portal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.portal.entidades.FaixaSalario;
import br.ufma.portal.repository.FaixaSalarioRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteFaixaSalario {
    @Autowired
    public
    FaixaSalarioRepo repo;

    @Test
    public void devecriaFaixaSalario(){
        //cenario
        FaixaSalario novo = FaixaSalario.builder().descricao("descricao").build();

        // acao
        FaixaSalario retorno = repo.save(novo);
        // verificacao
        Assertions.assertNotNull(retorno);

    }
}