package br.ufma.portal.model;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.repository.CursoRepo;

@SpringBootTest
public class TesteCurso {
    @Autowired
    public CursoRepo repo;

    @Test
    public void devecriaCurso() {
        // cenario
        Curso novo = Curso.builder().nome("CriarCurso").nivel("avançado").build();

        // acao
        Curso retorno = repo.save(novo);

        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome());
        Assertions.assertEquals(novo.getNivel(), retorno.getNivel());

        // Rollback
        repo.delete(retorno);

    }

    @Test
    public void deveremoverCurso() {
        // cenario
        Curso novo = Curso.builder().nome("RemoverCurso").nivel("avançado").build();
        Curso retorno = repo.save(novo);

        // acao
        Integer id = retorno.getId_curso();
        repo.deleteById(id);

        // verificação
        Optional<Curso> temp = repo.findById(id);
        Assertions.assertFalse(temp.isPresent());

    }

    @Test
    public void deveBuscarCurso() {
        // cenario
        Curso novo = Curso.builder().nome("BuscarCurso").nivel("avançado").build();
        Curso retorno = repo.save(novo);

        // acao
        Optional<Curso> temp = repo.findById(retorno.getId_curso());

        // verificacao
        Assertions.assertTrue(temp.isPresent());

        // Rollback
        repo.delete(retorno);

    }
}