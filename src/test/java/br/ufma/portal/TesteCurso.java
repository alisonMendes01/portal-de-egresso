package br.ufma.portal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.portal.entidades.Curso;
import br.ufma.portal.repository.CursoRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteCurso {
    @Autowired
    public
    CursoRepo repo;

    @Test
    public void devecriaCurso(){
        //cenario
        Curso novo = Curso.builder().nome("teste").nivel("avançado").build();

        // acao
        Curso retorno = repo.save(novo);
        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome()); 

    }
}