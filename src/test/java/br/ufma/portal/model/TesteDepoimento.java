package br.ufma.portal.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.repository.DepoimentoRepo;
import br.ufma.portal.model.repository.EgressoRepo;

@SpringBootTest
public class TesteDepoimento {
    @Autowired
    public DepoimentoRepo repoDepoimento;

    @Autowired
    public EgressoRepo repoEgresso;

    @Test
    public void devecriaDepoimento() {
        // cenario
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento")
                .email("email@email.com")
                .cpf("000")
                .resumo("resumo")
                .url_foto("urlaqui")
                .build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().texto("texto")
                .data(LocalDate.of(2022, Month.MAY, 15))
                .egresso_id(retornoEgresso)
                .build();

        // acao
        Depoimento retornoDepoimento = repoDepoimento.save(novoDepoimento);

        // verificacao
        Assertions.assertNotNull(retornoDepoimento);

        // Rollback
        repoDepoimento.delete(retornoDepoimento);
        repoEgresso.delete(retornoEgresso);

    }

    @Test
    public void deveremoverDepoimento() {
        // cenario
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento")
                .email("email@email.com")
                .cpf("000")
                .resumo("resumo")
                .url_foto("urlaqui")
                .build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().texto("texto")
                .data(LocalDate.of(2022, Month.MAY, 15))
                .egresso_id(retornoEgresso).build();

        // acao
        Depoimento retornoDepoimento = repoDepoimento.save(novoDepoimento);
        Integer id = retornoDepoimento.getId_depoimento();
        repoDepoimento.deleteById(id);
        // verificacao
        Assertions.assertNotNull(retornoDepoimento);

        // Rollback
        Optional<Depoimento> temp = repoDepoimento.findById(id);
        Assertions.assertFalse(temp.isPresent());

    }

    @Test
    public void deveBuscarDepoimento() {
        // cenario
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento")
                .email("email@email.com").cpf("000")
                .resumo("resumo")
                .url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().texto("texto")
                .data(LocalDate.of(2022, Month.MAY, 15))
                .egresso_id(retornoEgresso).build();

        Depoimento retornoDepoimento = repoDepoimento.save(novoDepoimento);
        // A????o

        Optional<Depoimento> temp = repoDepoimento.findById(retornoDepoimento.getId_depoimento());

        Assertions.assertTrue(temp.isPresent());

        // Rollback
        repoDepoimento.delete(retornoDepoimento);
        repoEgresso.delete(retornoEgresso);

    }

}