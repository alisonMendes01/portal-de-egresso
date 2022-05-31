package br.ufma.portal.entidade;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import br.ufma.portal.entidade.repository.CursoEgressoRepo;
import br.ufma.portal.entidade.repository.CursoRepo;
import br.ufma.portal.entidade.repository.EgressoRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteCursoEgresso {

    @Autowired
    public
    CursoEgressoRepo repoCursoEgresso;
    
    @Autowired
    public
    EgressoRepo repoEgresso;
    
    @Autowired
    public
    CursoRepo repoCurso;

    @Test
    public void devecriaCursoEgresso(){
        //cenario
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeCursoEgresso").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);

        Curso novoCurso = Curso.builder().nome("RemoverCurso").nivel("avançado").build();
        Curso retornoCurso = repoCurso.save(novoCurso);
        
        CursoEgresso novoCursoEgresso = CursoEgresso.builder().egresso_id(retornoEgresso).curso_id(retornoCurso).data_inicio(LocalDate.of(2022, Month.MAY, 15)).data_conclusao(LocalDate.of(2022, Month.MAY, 25)).build();
        // acao
        

        CursoEgresso retornoCursoEgresso = repoCursoEgresso.save(novoCursoEgresso);
        
        // verificacao
        Assertions.assertNotNull(retornoCursoEgresso);
        
        //Rollback
        repoCursoEgresso.delete(retornoCursoEgresso);
        repoEgresso.delete(retornoEgresso);
        repoCurso.delete(retornoCurso);
    }

    @Test
    public void deveremoverCursoEgresso(){
        //cenario
        //Cria Egresso
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeCursoEgresso").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);
        //Cria Curso
        Curso novoCurso = Curso.builder().nome("RemoverCurso").nivel("avançado").build();
        Curso retornoCurso = repoCurso.save(novoCurso);
        //Cria CursoEgresso
        CursoEgresso novoCursoEgresso = CursoEgresso.builder().egresso_id(retornoEgresso).curso_id(retornoCurso).data_inicio(LocalDate.of(2022, Month.MAY, 15)).data_conclusao(LocalDate.of(2022, Month.MAY, 25)).build();
        //Salva no banco
        CursoEgresso retornoCursoEgresso = repoCursoEgresso.save(novoCursoEgresso);

        //Ação
        CursoEgressoPk id = retornoCursoEgresso.getId();
        repoCursoEgresso.deleteById(id);

        //Verificação
        Optional<CursoEgresso> temp = repoCursoEgresso.findById(id);
        Assertions.assertFalse(temp.isPresent());
        
    }


    @Test
    public void deveobterCursoEgresso(){
        //cenario
        //Cria Curso
        Curso novoCurso = Curso.builder().nome("ObterCurso").nivel("avançado").build();
        Curso retornoCurso = repoCurso.save(novoCurso);
        //Cria Egresso
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeCursoEgresso").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);
        //Cria CursoEgresso
        CursoEgresso novoCursoEgresso = CursoEgresso.builder().egresso_id(retornoEgresso).curso_id(retornoCurso).data_inicio(LocalDate.of(2022, Month.MAY, 15)).data_conclusao(LocalDate.of(2022, Month.MAY, 25)).build();
        //Salva CursoEgresso
        CursoEgresso retornoCursoEgresso = repoCursoEgresso.save(novoCursoEgresso);

        // acao
        Optional<CursoEgresso> temp = repoCursoEgresso.findById(retornoCursoEgresso.getId());
        
        // verificacao
        Assertions.assertTrue(temp.isPresent());

        //Rollback
        repoCursoEgresso.delete(retornoCursoEgresso);
    }
    
}
