package br.ufma.portal.entidade;

import org.junit.jupiter.api.Test;

import java.util.Optional;

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
        Curso novo = Curso.builder().nome("CriarCurso").nivel("avançado").build();
        
        // acao
        Curso retorno = repo.save(novo);
        
        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome());
        Assertions.assertEquals(novo.getNivel(), retorno.getNivel());
        
        //Rollback
        repo.delete(retorno);

    }
    
    @Test
    public void deveremoverCurso(){
        //cenario
        Curso novo = Curso.builder().nome("RemoverCurso").nivel("avançado").build();
        Curso retorno = repo.save(novo);
        
        // acao
        Integer id = retorno.getId_curso();
        repo.deleteById(id);
        
        //verificação
        Optional<Curso> temp = repo.findById(id);  
        Assertions.assertFalse(temp.isPresent());

    }
    
    @Test
    public void deveobterCurso(){
        //cenario
        Curso novo = Curso.builder().nome("ObterCurso").nivel("avançado").build();
        Curso retorno = repo.save(novo);

        // acao
        Optional<Curso> temp = repo.findById(retorno.getId_curso());
        
        // verificacao
        Assertions.assertTrue(temp.isPresent());

        //Rollback
        repo.delete(retorno);

    }
}