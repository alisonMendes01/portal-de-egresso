package br.ufma.portal.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.Curso;
import br.ufma.portal.model.CursoEgresso;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.repository.CursoEgressoRepo;
import br.ufma.portal.model.repository.CursoRepo;
import br.ufma.portal.model.repository.EgressoRepo;
import br.ufma.portal.service.CursoService;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@SpringBootTest
public class TesteCursoService {
    @Autowired
    public EgressoRepo repoEgresso;

    @Autowired
    public CursoRepo repoCurso;

    @Autowired
    public CursoEgressoRepo repoCursoEgresso;

    @Autowired
    CursoService service;

    @Test
    public void deveGerarErroSalvarSemNome() {
        // cenario
        Curso novo = Curso.builder().nivel("avançado").build();

        // acao
        RegraNegocioRunTime assertt = Assertions.assertThrows(RegraNegocioRunTime.class,
                () -> service.salvar(novo));
        System.out.println(assertt.getMessage());

    }

    @Test
    public void deveObterEgressoPorCurso() {
        Egresso egresso1 = Egresso.builder()
                .nome("Maria")
                .email("email@email.com")
                .cpf("000")
                .resumo("resumo")
                .url_foto("urlaqui")
                .build();
        Egresso retornoEgresso1 = repoEgresso.save(egresso1);

        Egresso egresso2 = Egresso.builder()
                .nome("Pedro")
                .email("email@email.com")
                .cpf("000")
                .resumo("resumo")
                .url_foto("urlaqui")
                .build();
        Egresso retornoEgresso2 = repoEgresso.save(egresso2);

        Egresso egresso3 = Egresso.builder()
                .nome("Jose")
                .email("email@email.com")
                .cpf("000")
                .resumo("resumo")
                .url_foto("urlaqui")
                .build();
        Egresso retornoEgresso3 = repoEgresso.save(egresso3);

        Curso curso1 = Curso.builder()
                .nome("microllins")
                .nivel("avançado")
                .build();
        Curso retornoCurso1 = repoCurso.save(curso1);

        Curso curso2 = Curso.builder()
                .nome("microllins")
                .nivel("avançado")
                .build();
        Curso retornoCurso2 = repoCurso.save(curso2);

        CursoEgresso cursoEgresso1 = CursoEgresso.builder()
                .egresso_id(egresso1)
                .curso_id(retornoCurso1)
                .data_inicio(LocalDate.of(2022, Month.MAY, 15))
                .data_conclusao(LocalDate.of(2022, Month.MAY, 25))
                .build();
        CursoEgresso retornoCursoEgresso1 = repoCursoEgresso.save(cursoEgresso1);

        CursoEgresso cursoEgresso2 = CursoEgresso.builder()
                .egresso_id(retornoEgresso2)
                .curso_id(retornoCurso2)
                .data_inicio(LocalDate.of(2022, Month.MAY, 15))
                .data_conclusao(LocalDate.of(2022, Month.MAY, 25))
                .build();
        CursoEgresso retornoCursoEgresso2 = repoCursoEgresso.save(cursoEgresso2);

        CursoEgresso cursoEgresso3 = CursoEgresso.builder()
                .egresso_id(retornoEgresso3)
                .curso_id(retornoCurso1)
                .data_inicio(LocalDate.of(2022, Month.MAY, 15))
                .data_conclusao(LocalDate.of(2022, Month.MAY, 25))
                .build();
        CursoEgresso retornoCursoEgresso3 = repoCursoEgresso.save(cursoEgresso3);

        List<Egresso> egressos = service.findByEgresso(curso1.getId_curso());

        int index = 1;
        for (Egresso egresso : egressos) {
            System.out.println("EGRESSO " + (index++) + ": " + egresso.getNome());
        }
    }

    @Test
    public void deveObterNumeroEgressosByCurso() {
        Egresso egresso1 = Egresso.builder()
                .nome("Maria")
                .email("email@email.com")
                .cpf("000")
                .resumo("resumo")
                .url_foto("urlaqui")
                .build();
        Egresso retornoEgresso1 = repoEgresso.save(egresso1);

        Egresso egresso2 = Egresso.builder()
                .nome("Pedro")
                .email("email@email.com")
                .cpf("000")
                .resumo("resumo")
                .url_foto("urlaqui")
                .build();
        Egresso retornoEgresso2 = repoEgresso.save(egresso2);

        Egresso egresso3 = Egresso.builder()
                .nome("Jose")
                .email("email@email.com")
                .cpf("000")
                .resumo("resumo")
                .url_foto("urlaqui")
                .build();
        Egresso retornoEgresso3 = repoEgresso.save(egresso3);

        Curso curso1 = Curso.builder()
                .nome("microllins")
                .nivel("avançado")
                .build();
        Curso retornoCurso1 = repoCurso.save(curso1);

        Curso curso2 = Curso.builder()
                .nome("microllins")
                .nivel("avançado")
                .build();
        Curso retornoCurso2 = repoCurso.save(curso2);

        CursoEgresso cursoEgresso1 = CursoEgresso.builder()
                .egresso_id(egresso1)
                .curso_id(retornoCurso1)
                .data_inicio(LocalDate.of(2022, Month.MAY, 15))
                .data_conclusao(LocalDate.of(2022, Month.MAY, 25))
                .build();
        CursoEgresso retornoCursoEgresso1 = repoCursoEgresso.save(cursoEgresso1);

        CursoEgresso cursoEgresso2 = CursoEgresso.builder()
                .egresso_id(retornoEgresso2)
                .curso_id(retornoCurso2)
                .data_inicio(LocalDate.of(2022, Month.MAY, 15))
                .data_conclusao(LocalDate.of(2022, Month.MAY, 25))
                .build();
        CursoEgresso retornoCursoEgresso2 = repoCursoEgresso.save(cursoEgresso2);

        CursoEgresso cursoEgresso3 = CursoEgresso.builder()
                .egresso_id(retornoEgresso3)
                .curso_id(retornoCurso1)
                .data_inicio(LocalDate.of(2022, Month.MAY, 15))
                .data_conclusao(LocalDate.of(2022, Month.MAY, 25))
                .build();
        CursoEgresso retornoCursoEgresso3 = repoCursoEgresso.save(cursoEgresso3);

        Integer numeroEgressos = service.countEgressosByCurso(curso1.getId_curso());
        System.out.println("NUMERO DE EGRESSOS NO CURSO " + curso1.getNome() +": "+ numeroEgressos);
    }
}