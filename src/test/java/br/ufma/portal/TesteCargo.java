package br.ufma.portal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.portal.entidades.Cargo;
import br.ufma.portal.repository.CargoRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteCargo {
    @Autowired
    public
    CargoRepo repo;

    @Test
    public void devecriaCurso(){
        //cenario
        Cargo novo = Cargo.builder().nome("teste").descricao("descrição").build();

        // acao
        Cargo retorno = repo.save(novo);
        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome()); 

    }
}